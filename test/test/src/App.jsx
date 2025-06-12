import React, { useState } from "react";

const serviceKey = "eLcAi6EheQARtaiHDeQN%2Bkg2wAus4IF6uAHfE5gE4g5yCga5jSh9%2BEGNJRh6m1FpEU4xmGzL3yh9GcLCk3MOXQ%3D%3D";

export default function App() {
  const [keyword, setKeyword] = useState("");
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);

  const searchAPI = async () => {
    if (!keyword) return alert("검색어를 입력하세요");

    const url = `https://api.odcloud.kr/api/gov24/v3/serviceList?serviceKey=${serviceKey}&searchWrd=${encodeURIComponent(keyword)}&page=1&perPage=10&returnType=JSON`;

    console.log("API 호출 URL:", url);
    
    try {
      const res = await fetch(url);
      if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);

      const data = await res.json();
      setResult(data);
      setError(null);
    } catch (err) {
      setError(err.message);
      setResult(null);
    }
  };

  return (
    <div style={{ padding: 20 }}>
      <h1>공공 API 검색</h1>
      <input
        type="text"
        placeholder="검색어 입력"
        value={keyword}
        onChange={(e) => setKeyword(e.target.value)}
      />
      <button onClick={searchAPI}>검색</button>

      {error && <p style={{ color: "red" }}>에러: {error}</p>}

      {result && (
        <div>
          <h2>검색 결과:</h2>
          <pre style={{ whiteSpace: "pre-wrap", wordBreak: "break-word" }}>
            {JSON.stringify(result, null, 2)}
          </pre>
        </div>
      )}
    </div>
  );
}
