import React, { useState } from "react";
import LinkComponent from "./LinkComponent";
import axios from "axios";

function proxyTest() {
    axios.get("/test")
        .then((res) => {
            console.log(res.data);
        })
        .catch((err) => {
            console.error(err);
        });
}

function getSession() {
    axios.get("/getSession")
        .then((res) => {
            console.log(res.data);
        })
        .catch((err) => {
            console.error(err);
        });
}

function getPubKey(setPemKey) {
    axios.post("/getPubKey")
        .then((res) => {
            console.log(res.data);
            setPemKey(res.data); // PEM 형식 공개키 설정
        })
        .catch((err) => {
            console.error(err);
        });
}

// String -> ArrayBuffer 변환
function str2ab(str) {
    const buf = new ArrayBuffer(str.length);
    const bufView = new Uint8Array(buf);
    for (let i = 0, strlen = str.length; i < strlen; i++) {
        bufView[i] = str.charCodeAt(i);
    }
    return buf;
}

// PEM 형식의 공개키를 가져와 CryptoKey로 변환하는 함수
async function setCrypto(pemKey) {
    if (pemKey === "") {
        pemKey = `MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA70hmy3OJ6YmFIX/fmwE5hK+pC5xOvg2mmYzA9K763KDA1M+p314CWTWV4KZHyDvBzYXpR6iaTCQxcH2aWkoT/TBtHsQl44vKlyVY8OQtPQMIdqXZ/r2Yt/g5IAZXi/aobmLsp9fT+KUsrSGzocerYtx25XGUMrkri83CuCRwVZaXGXuvHjjK4YTIP6cMiQE/pVQvgOY32QR6boD8AisnNLQ+UmuGJeFVn4WF8JabOwMrpYwH1EQgxnzszpM6Ry5GBIA8UImtKmrWRmYg9Dru3wmAG47cosMt4Z2peQb62tIHylYXAvS6EE7v5BRSz2YWquOf+Oouk1pyNJxXd6yz3QIDAQAB`;
    }

    // PEM 포맷을 Uint8Array로 변환 
    const binaryDerString = window.atob(pemKey);
    const binaryDer = str2ab(binaryDerString);

    try {
        const cryptoKey = await window.crypto.subtle.importKey(
            "spki",
            binaryDer,
            {
                name: "RSA-OAEP",
                hash: "SHA-256",
            },
            true, // 키 추출 유무 ( true면 추출 가능)
            ["encrypt"]
        );

        return cryptoKey; // 가져온 cryptoKey 반환
    } catch (err) {
        console.error("Error importing key:", err);
    }
}

// 암호화 함수
async function encryptData(plainData, cryptoKey) {
    const encoder = new TextEncoder();
    const encodedData = encoder.encode(plainData);

    try {
        const encryptedData = await window.crypto.subtle.encrypt(
            {
                name: "RSA-OAEP",
            },
            cryptoKey,
            encodedData
        );

        console.log("Encrypted data:", new Uint8Array(encryptedData));
        return new Uint8Array(encryptedData);
    } catch (err) {
        console.error("Encryption error:", err);
    }
}

function TestComponent() {
    const [pemKey, setPemKey] = useState("");
    const [plainData, setPlainData] = useState("");

    const handleEncrypt = async () => {
        const cryptoKey = await setCrypto(pemKey);
        if (cryptoKey) {
            const encryptedData = await encryptData(plainData, cryptoKey);
            console.log("Encrypted Data:", encryptedData);
            // 여기서 암호화된 데이터를 서버에 보내거나 처리할 수 있음
        }
    };

    return (
        <>
            <LinkComponent />
            <h1>TestComponent</h1>
            <div
                style={{
                    display: 'flex',
                    flexDirection: 'column',
                    gap: '10px',
                    width: '200px'
                }}
            >
                <input
                    type="text"
                    id="userid"
                    name="userid"
                    placeholder="userid"
                />
                <input
                    type="password"
                    id="password"
                    name="password"
                    placeholder="userpw"
                />
                <button onClick={proxyTest}>login</button>
                <button onClick={getSession}>getSession</button>
                <button onClick={() => getPubKey(setPemKey)}>getPubKey</button>
                <input
                    type="text"
                    id="plainData"
                    value={plainData}
                    onChange={(e) => setPlainData(e.target.value)} // 사용자 입력을 상태에 저장
                    placeholder="Enter data to encrypt"
                />
                <button onClick={handleEncrypt}>Encrypt</button>
            </div>
        </>
    );
}

export default TestComponent;
