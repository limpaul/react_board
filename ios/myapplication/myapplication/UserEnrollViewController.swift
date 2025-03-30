//
//  UserEnrollViewController.swift
//  myapplication
//
//  Created by 임바울 on 3/23/25.
//

import UIKit


class UserEnrollViewController: UIViewController, UITextFieldDelegate, UIAdaptivePresentationControllerDelegate, RestaurantEnrollViewControllerDelegate {

    @IBOutlet weak var companyNameLabel: UILabel!
    
    @IBOutlet weak var usernameTextField: UITextField!
    
    @IBOutlet weak var useremailTextField: UITextField!

    @IBOutlet weak var userpasswordTextFied: UITextField!
    
    @IBOutlet weak var userpasswordVerifyTextField: UITextField!
    
    @IBOutlet weak var userRoleChkBox: UISwitch!
    
    @IBOutlet weak var roleRestaurantClientChkBox: UISwitch!
    
    var userData: [String: Any] = [:]
    
    override func viewDidLoad() {
        super.viewDidLoad()
        lotatingLabel()
        textFieldInit()
        userRoleChkBox.isEnabled = false
        roleRestaurantClientChkBox.isOn = false
        
        roleRestaurantClientChkBox.addTarget(self, action: #selector(switchValueChanged), for: .valueChanged)
        
        
    
    }

    
    @objc func switchValueChanged(sender: UISwitch){
        if sender.isOn{
            performSegue(withIdentifier: "toRestaurantErollViewController", sender: self)
        }else{
            
        }
    }
    
    
    @IBAction func userAddSendBtn(_ sender: Any) {
        var username = usernameTextField.text
        var useremail = useremailTextField.text
        var userpassword = userpasswordTextFied.text
        if usernameTextField.text?.isEmpty == true {
            username = "test"
        }
        if useremailTextField.text?.isEmpty == true {
            useremail = "test@test.com"
        }
        if userpasswordTextFied.text?.isEmpty == true{
            userpassword = "1234"
        }
        userData["username"] = username
        userData["useremail"] = useremail
        userData["userpassword"] = userpassword
        
    }
    
    @IBAction func userAddAutoEnrollBtn(_ sender: UIButton) {
        
        Task {
            await NetworkManager
                .shared
                .requestPOST(urlPath: "/api/order/user/enroll/account",
                        requestBody: [
                            "username":"bwlim",
                            "useremail":"bwlim@naver.com",
                            "userpassword":"1234",
                            "restaurantName":"bwlimRestaurant",
                            "restaurantAddress":"bwlimRestaurantAddress"
                        ]){
                            success, result in
                            if success == true{
                                if let resultCode = result["resultCode"] as? Int {
                                    if resultCode == 1 {
                                        print("회원가입 성공")
                                        DispatchQueue.main.async {
                                            self.dismiss(animated: true)
                                        }
                                    } else if resultCode == 2 {
                                        print("이미 존재하는 회원입니다")
                                    }
                                }
                            }
                        }
        }
    }
    

    
    func sendData(_ data: String) {
        print("받은 데이터: \(data)")
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
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    func textFieldInit(){
        for subview in view.subviews where subview is UITextField{
            (subview as! UITextField).delegate = self
        }
    }
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "toRestaurantErollViewController" {
            if let destinationVC = segue.destination as? RestaurantErollViewController {
                destinationVC.delegate = self // delegate 설정
            }
        }
    }
    // 델리게이트 메서드 구현
    func didReturnData(_ data: [String: Any]) {
        if let restaurantName = data["restaurantName"] as? String,
           let restaurantAddress = data["restaurantAddress"] as? String {
            print("Received Restaurant Name: \(restaurantName)")
            print("Received Restaurant Address: \(restaurantAddress)")
            userData["restaurantName"] = restaurantName
            userData["restaurantAddress"] = restaurantAddress
        }
    }
    // delegate method
    func modalDidDismiss(_ isUserEnrollBtnClicked: Bool) {
        if isUserEnrollBtnClicked == false{
            roleRestaurantClientChkBox.isOn = false
        }
    }
    
}
