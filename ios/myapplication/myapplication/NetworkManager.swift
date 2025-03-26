//
//  NetworkManager.swift
//  myapplication
//
//  Created by 임바울 on 3/23/25.
//

import Foundation
import os

class NetworkManager {
    // 싱글톤 패턴: 앱 전체에서 하나의 인스턴스를 공유
    static let shared = NetworkManager()
    
    static let serverIp = "172.30.1.7"
    static let serverPort = 8080
    let logger = Logger(subsystem: "com.raonsecure.bwlim", category: "network")
    private init() {} // 외부에서 인스턴수 생성을 막기 위한 초기화 방법
    
    func requestPOST(urlPath: String, requestBody: Any ,completion: @escaping (Bool, [String : Any])->Void) async{
        let urlStr = "http://\(NetworkManager.serverIp):\(NetworkManager.serverPort)\(urlPath)"
        logger.info("request POST \(urlPath)")
        guard let url = URL(string: urlStr) else {return}
        
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        request.httpBody = try? JSONSerialization.data(withJSONObject: requestBody)
        
        do{
            let (data, response) = try await URLSession.shared.data(for: request)
            if let httpResponse = response as? HTTPURLResponse {
                self.logger.info("\(urlPath) response code: \(httpResponse.statusCode)")
            }
            if let response = try JSONSerialization.jsonObject(with: data, options: []) as? [String : Any]{
                logger.info("response POST \(url) : \(response)")
                completion(true, response)
            }
        }catch{
            self.logger.error("error: \(urlPath) \(error.localizedDescription)")
            completion(false, [:])
            return
        }
        
        
    }
    func requestGET(urlPath: String, completion: @escaping (Bool, [String : Any])->Void) async {
        let urlStr = "http://\(NetworkManager.serverIp):\(NetworkManager.serverPort)\(urlPath)"
        guard let url = URL(string: urlStr) else {return}
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
        do{
            let (data, response) = try await URLSession.shared.data(for: request)
            if let httpResponse = response as? HTTPURLResponse {
                self.logger.error("response GET \(url) response status code : \(httpResponse.statusCode)")
            }
            if let response = try JSONSerialization.jsonObject(with: data, options: []) as? [String: Any]{
                logger.info("response GET \(url) : \(response)")
                completion(true, response)
            }
            
        }catch{
            self.logger.error("error: \(urlPath) \(error.localizedDescription)")
            completion(false, [:])
            return
        }
    }
    
    func requestGET2(urlPath: String, completion: @escaping (Bool, Any?)->Void){
        let urlStr:String = "http://\(NetworkManager.serverIp):\(NetworkManager.serverPort)\(urlPath)"
        print("GET \(urlStr)")
        guard let url = URL(string: urlStr) else {return}
        
        var request = URLRequest(url: url)
        request.httpMethod = "GET"
        
        URLSession.shared.dataTask(with: request){
            data, response, error in
            if let error = error {
                print("error accur \(error)")
                completion(false, nil)
                return
            }
            
            guard let data = data else {
                print("data is nil")
                completion(false, nil)
                return
            }
            
            do{
                if let httpResponse = response as? HTTPURLResponse {
                    let contentType = httpResponse.allHeaderFields["Content-Type"] as? String
                    
                    if contentType?.contains("application/json") == true {
                        let json = try JSONSerialization.jsonObject(with: data, options: [])
                        completion(true, json)
                    }else{
                        if let stringData = String(data: data, encoding: .utf8) {
                            completion(true, stringData)
                        }else{
                            print("문자열 변환 안됨")
                            completion(false, nil)
                        }
                    }
                }
                
                
            }catch{
                print("JSON 파싱 오류: \(error)")
                completion(false, nil)
            }
            
        }.resume()
        
    }
}
