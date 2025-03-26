//
//  RestaurantErollViewController.swift
//  myapplication
//
//  Created by 임바울 on 3/23/25.
//

import UIKit

protocol RestaurantEnrollViewControllerDelegate:AnyObject {
    func didReturnData(_ data: [String: Any])
}

class RestaurantErollViewController: UIViewController {

    @IBOutlet weak var companyNameLabel: UILabel!
    
    @IBOutlet weak var restaurantName: UITextField!
    
    @IBOutlet weak var restaurantAddress: UITextField!
    
    @IBOutlet weak var agreeContractUISwitch1: UISwitch!
    
    @IBOutlet weak var agreeContractUISwitch2: UISwitch!
    
    @IBOutlet weak var agreeContractUISwitch3: UISwitch!
    
    weak var delegate: RestaurantEnrollViewControllerDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
        self.dismiss(animated: true, completion: nil)
    }
}
