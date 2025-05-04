// 쿠키에 저장된 이메일을 input창에 입력해놓기
// 로그인이 안된 경우에 수행

// 쿠키에서 매개변수로 전달받은 key와 일치하는 value 얻어와 반환하는 함수
const getCookie = (key) => {
  const cookie = document.cookie;
  
  console.log(cookie);
  
  // cookies 에 저장된 문자열을 배열 형태로 변환
  const cookieList = cookie.split("; ").map(el => el.split("="))
  
  console.log(cookie);
  
  // 배열.map(함수) : 배열의 각 요소를 이용해 콜백함수 수행 후
  //                결과 값으로 새로운 배열을 만들어서 반환하는 JS 내장 함수	
  
  /*
  [
    ['saveId', 'user01@kh.or.kr'],
    ['test', 'testValue']
    ]
    2차원 배열 형태임
    */
   // 배열 -> JS 객체로 변환 (그래야 다루기 쉬움)
   
   const obj = {}; // 비어있는 객체 선언
   
   for(let i = 0 ; i < cookieList.length ; i++) {
     const K = cookieList[i][0];
     const V = cookieList[i][1];
     
     obj[K] = V;
    }
    
    console.log(obj);
    
    return obj[key];
  };

// 이메일 작성 input 요소
const loginEmail = document.querySelector("#loginform input[name ='memberEmail']");

if(loginEmail != null) {

  const saveId = getCookie("saveId");

  if(saveId != undefined){

    loginEmail.value = saveId;

    document.querySelector("#input [name = 'saveId']").Checked = true;

  }
}
/*
// 이메일, 비밀번호가 없는 상태에서 로그인 버튼을 누를 시

const loginPw = document.querySelector("input[name='memberPw']");

if(loginEmail.value.trim().length === 0 || loginPw.value.trim().length === 0 ){
  alert("이메일과 비밀번호를 모드 입력해주세요.");
}
*/
