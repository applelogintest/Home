import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class CreateJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //최종 완성될 JSONObject 선언(전체)
        JSONObject jsonObject = new JSONObject();
 
        //person의 JSON정보를 담을 Array 선언
        JSONArray personArray = new JSONArray();
        JSONArray airports=new JSONArray();
        
 
        //person의 한명 정보가 들어갈 JSONObject 선언
        JSONObject personInfo = new JSONObject();
 
        //정보 입력
        personInfo.put("name", "송강호");
        personInfo.put("age", "25");
        personInfo.put("gender", "남자");
        personInfo.put("nickname", "남궁민수");
        //Array에 입력
        personArray.add(personInfo);
 
        personInfo = new JSONObject();
        personInfo.put("name", "전지현");
        personInfo.put("age", "21");
        personInfo.put("gender", "여자");
        personInfo.put("nickname", "예니콜");
        personArray.add(personInfo);
 
        //전체의 JSONObject에 사람이란 name으로 JSON의 정보로 구성된 Array의 value를 입력
        jsonObject.put("persons", personArray);
 
        
        JSONArray bookArray = new JSONArray();
 
        JSONObject bookInfo = new JSONObject();
 
        bookInfo.put("name", "사람은 무엇으로 사는가?");
        bookInfo.put("writer", "톨스토이");
        bookInfo.put("price", "100");
        bookInfo.put("genre", "소설");
        bookInfo.put("publisher", "톨스토이 출판사");
        bookArray.add(bookInfo);
 
        bookInfo = new JSONObject();
        bookInfo.put("name", "홍길동전");
        bookInfo.put("writer", "허균");
        bookInfo.put("price", "300");
        bookInfo.put("genre", "소설");
        bookInfo.put("publisher", "허균 출판사");
        bookArray.add(bookInfo);
 
        bookInfo = new JSONObject();
        bookInfo.put("name", "레미제라블");
        bookInfo.put("writer", "빅토르 위고");
        bookInfo.put("price", "900");
        bookInfo.put("genre", "소설");
        bookInfo.put("publisher", "빅토르 위고 출판사");
        bookArray.add(bookInfo);
 
        jsonObject.put("books", bookArray);
 
        //JSONObject를 String 객체에 할당
        String jsonInfo = jsonObject.toJSONString();
 
        System.out.print(jsonInfo);

	}

}
