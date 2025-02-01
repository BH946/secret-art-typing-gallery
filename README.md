# Intro

**디자인학과 친구와 협업 프로젝트**

* **바로가기 : [Secret Art Typing Gallery](http://satg.co.kr/)**
* **디자인 : [피그마](https://www.figma.com/file/GMjReOD87LsgUtoI1cFCry/%EC%A1%B8%EC%9E%91-%EC%9B%B9%EC%82%AC%EC%9D%B4%ED%8A%B8-%EB%94%94%EC%9E%90%EC%9D%B8?type=design&node-id=14-671&mode=design)**

<br><br>

# Secret Art Typing Gallery

**이 프로젝트의 핵심은 "남들에게 할 수 없는 말과 욕설 등등" 을 마음껏 표출하여 마음의 부담을 덜어주는 것**

* "글자" -> "이미지" 형태로 변환함으로써 무슨 말인지 추적 불가
* 글자 입력시 이쁜 작품으로 나타내기 위해 "제작한 폰트" 를 사용
* 자세히 보고싶다면 위의 "바로가기" 를 참고

<br><br>

## Used Skills & Version

* **Java 17**
* IDE : IntelliJ
* **Spring Boot : 3.0.2**
* Packaging : Jar
* Build Tool : Gradle - Groovy
* **View Template : Thymeleaf**
* Hosting : cafe24

<br><br>

## Product(제품)

### Concept

![image](https://github.com/BH946/secret-art-typing-gallery/assets/80165014/38d898fa-3d2e-44a9-9116-2a89cff76332) 

<br>

### Studio

![image](https://github.com/BH946/secret-art-typing-gallery/assets/80165014/1ed0d37a-2030-48a9-9e29-c2da466b18b1) 

<br>

### Gallery

![image](https://github.com/BH946/secret-art-typing-gallery/assets/80165014/41cae106-1bf3-4c01-9931-1c54de73e147) 

<br><br>

# Folder Structure

- **[`/src/main/resources/application.yml`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/resources/application.yml)**

  - **프로필 설정 - default, prod** 
  - db, messages, 외부설정 등등

- **[`/build.gradle`](https://github.com/BH946/secret-art-typing-gallery/blob/main/build.gradle)**

  - **의존성 설정**

- **[`/src/main/java/com/dau/secretarttypinggallery/SecretArtTypingGalleryApplication.java`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/SecretArtTypingGalleryApplication.java)**

  - **메인 함수! 제일 먼저 실행!**

- **엔티티/레퍼지토리/서비스/컨트롤러 개발**

  - **[`/entity/Item.java`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/entity/Item.java)**
  - **[`/repository/ItemRepository.java`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/repository/ItemRepository.java)**
  - **[`/service/ItemService.java`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/service/ItemService.java)**
    - @Cacheable, @CachePut 활용
  - **[`/controller/`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/controller/)**
    - Gallery, Home, Studio 컨트롤러 구성
    - 페이지 별 게시판 부분은 캐시메모리 활용
    - PRG 패턴 적용
    - "글자" -> "이미지" 변환 적용 등

- **설정 파트**

  - **[`/config/MyDataSourceConfig.java`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/config/MyDataSourceConfig.java)**
    - **[`/datasource/MyDataSourceProperties.java`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/datasource/MyDataSourceProperties.java)**
    - 함께 세트로 사용 - 외부설정 등록역할

  - **[`/config/WebMvcConfig.java`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/java/com/dau/secretarttypinggallery/config/WebMvcConfig.java)**
    - 정적 리소스 캐시 추가 및 경로 핸들링

- **[`/src/main/resources/templates/`](https://github.com/BH946/secret-art-typing-gallery/blob/main/src/main/resources/templates/)**

  - **다양한 html -> error 페이지 포함**
  - **index.html : 제일 처음 메인 소개화면**
  - **lobby.html : 처음 화면 다음인 로비 화면**
  - **gallery.html : 갤러리(전시회) 페이지별 화면**
  - **gallery-item.html : Item 세부정보**
  - **studio.html : 작품제작실(스튜디오) 화면**
  - **studio-complete.html : 작품 제작완료 화면**
  - **fragments -> 이부분을 많이사용중이라 이곳을 대부분 수정**
    * **head.html**
    * **header.html**
    * **footer.html**
    * **album.html**
    * **modal.html**
    * **scripts.html**
    * **item.html**
