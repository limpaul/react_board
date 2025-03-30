//
//  RestaurantErollViewController.swift
//  myapplication
//
//  Created by 임바울 on 3/23/25.
//

import UIKit

protocol RestaurantEnrollViewControllerDelegate:AnyObject {
    func didReturnData(_ data: [String: Any])
    func modalDidDismiss(_ isUserEnrollBtnClicked: Bool)
}

class RestaurantErollViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var companyNameLabel: UILabel!
    
    @IBOutlet weak var restaurantName: UITextField!
    
    @IBOutlet weak var restaurantAddress: UITextField!
    
    @IBOutlet weak var agreeContractUISwitch1: UISwitch!
    
    @IBOutlet weak var agreeContractUISwitch2: UISwitch!
    
    @IBOutlet weak var agreeContractUISwitch3: UISwitch!
    
    weak var delegate: RestaurantEnrollViewControllerDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.presentationController?.delegate = self
        lotatingLabel()
        initUISwitch()
        
    }
    
    func initUISwitch(){ // 계약 동의 OFF상태 초기화
        agreeContractUISwitch1.isOn = false
        agreeContractUISwitch2.isOn = false
        agreeContractUISwitch3.isOn = false
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
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        self.view.endEditing(true)
    }
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func enrollRestaurantBtn(_ sender: UIButton) {
        if restaurantName.text?.isEmpty == true{
            restaurantName.text = "test restaurant name"
        }
        if restaurantAddress.text?.isEmpty == true{
            restaurantAddress.text = "test restaurant address"
        }
        let returnedData: [String : Any] = [
            "restaurantName": restaurantName.text!,
            "restaurantAddress":restaurantAddress.text!
        ]
        delegate?.didReturnData(returnedData)
        delegate?.modalDidDismiss(true)
        self.dismiss(animated: true, completion: nil)
    }
    
}

extension RestaurantErollViewController: UIAdaptivePresentationControllerDelegate {
    func presentationControllerDidDismiss(_ presentationController: UIPresentationController) {
        delegate?.modalDidDismiss(false)
    }
}
