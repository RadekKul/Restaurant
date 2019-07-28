# Restauracja (w trakcie rozwoju)

Restauracja to aplikacja umożliwiająca zarejestrowanemu użytkownikowi rezerwację stolika na wybraną godzinę oraz zamówienia posiłku. 

Administrator ma możliwość dodawania nowych produktów / dań / menu oraz stolików, które  są przechowywane w bazie danych.

Najważniejsze funkcjonalności aplikacji:
  - Rejestracja użytkowników
  - Tworzenie rezerwacji stolików
  - Tworzenie zamówień posiłków, przypisanych do użytkownika oraz do stolika
  - Możliwość modyfikacji bazy produktów oraz asortymentu restauracji przez administratora
  - Zarządzanie swoimi planami
  - Zarządzanie rezerwacjami oraz bazą danych
  - I inne...

### Stack technologiczny

W projekcie wykorzystywane są następujące technologie:

* Java 8 - Backend aplikacji
* Spring Boot - Umożliwia zbudowanie aplikacji webowej bazującej na Spring
* MySQL - Silnik bazy danych
* Hibernate - Zapewnienia translację i walidacje danych pomiędzy relacyjną bazą danych, a światem obiektowym
* Bootstrap, jQuery, JSP - Frontend aplikacji
* Intellij IDEA - IDE wykorzystane do pisania kodu
* MySQL Workbench - Narzędzie do zarządzania i modelowania bazy danych MySQL
* Git, GitHub - Repozytorium
* Tomcat - Serwer aplikacji
* Maven - Narzędzie automatyzujące budowę oprogramowania na platformę Java

### Instalacja

Aby uruchomić aplikację należy:
* Sklonować repozytorium
* Otworzyć repozytorium za pomocą IDE 
* Uruchomić wbudowany serwer Tomcat, który automatycznie zbuduje i zdeployuje aplikację
* Przykładowa baza danych dostępna jest na stronie: https://remotemysql.com/phpmyadmin/
  login i hasło do serwisu dostępne są w pliku z właściwościami aplikacji (application.properties).
  
### Dane logowania

Przy uruchomieniu aplikacji z "domyślną" bazą danych możemy skorzystać z użytkowników:

Administrator
L: admin
P: admin

User
L: user1
P: user


