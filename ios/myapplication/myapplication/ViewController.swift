//
//  ViewController.swift
//  myapplication
//
//  Created by 임바울 on 3/16/25.
//

import UIKit
import os

class ViewController: UIViewController, UITextFieldDelegate {
    
    let logger = Logger(subsystem: "com.raonsecure.bwlim", category: "UserLoginController")
    
    @IBOutlet weak var companyNameLabel: UILabel!
    
    @IBOutlet weak var username: UITextField!
    
    @IBOutlet weak var userpassword: UITextField!
    
    @IBOutlet weak var userEnrollText: UILabel!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lotatingLabel() // label roatating
        initTextField() //
        userEnrollText.isUserInteractionEnabled = true
        let tapGesture = UITapGestureRecognizer(target: self, action: #selector(labelTabbed))
        userEnrollText.addGestureRecognizer(tapGesture)
    }
    
    
    
    
    
    
    
    @IBAction func userLoginBtn(_ sender: UIButton) {
        
    }
    
    @IBAction func autoUserLoginBtn(_ sender: UIButton) {
        username.text = "test"
        userpassword.text = "1234"
        
    }
    
    
    @objc func labelTabbed(){ // 회원 가입 label을 클릭 할 경우
        performSegue(withIdentifier: "toUserEnrollViewController", sender: self)
    }
    
    
    @IBAction func autoLoginBtn(_ sender: Any) {
        Task {
            let success = await NetworkManager
                .shared
                .requestPOST(urlPath: "/api/order/user/login",
                             requestBody: [
                                "username":"test",
                                "userpassword":"1234"]){
                                    success, result in
                                    if(success){
                                        if let isSuccess = result["isSuccess"] as? Int, let role = result["role"] as? String {
                                            print("isSuccess: \(isSuccess), role: \(role)")
                                        }
                                    }
                                }
            
            DispatchQueue.main.async {
                print("UI working ...")
            }
        }
    }
    
    func lotatingLabel(){
        let rotation = CABasicAnimation(keyPath: "transform.rotation")
        rotation.toValue = NSNumber(value: Double.pi * 2) // 360도 회전
        rotation.duration = 5.0
        rotation.repeatCount = .infinity
        rotation.isRemovedOnCompletion = false
        rotation.fillMode = .forwards
        companyNameLabel.layer.add(rotation, forKey: "rotateAnimation")
    }
    func initTextField(){
        username.delegate = self
        userpassword.delegate = self
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
   
}

