//
//  UserEnrollViewController.swift
//  myapplication
//
//  Created by 임바울 on 3/23/25.
//

import UIKit

class UserEnrollViewController: UIViewController, UITextFieldDelegate, RestaurantEnrollViewControllerDelegate {

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
        usernameTextField.delegate = self
        useremailTextField.delegate = self
        userpasswordTextFied.delegate = self
        userpasswordVerifyTextField.delegate = self
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
}
