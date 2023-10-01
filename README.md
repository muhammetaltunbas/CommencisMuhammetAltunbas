# CommencisMuhammetAltunbas
Commencis UI Automation Work

**Proje içeriği**
Selenium framework
Written in Java
Executes with TestNG
Built on Maven
Uses BDD Approach (Cucumber)
Follows “Page Object Model”
Logging
Reporting

**Projeyi nasıl çalıştırabilirim?**

1- Projede maven kullandığım için termial'den proje dizininde "**mvn test -PBddTest**" komutununu çalıştırdığımızda proje run ediliyor. Ayrıca proje'de Bdd kullandığım için, ide üzerinden case bazlı run edebiliyoruz.
2- Ayrıca "testngCucumber.xml" dosyası içerisine girip sağ tık -> Run dediğimizde de proje run edilir.

**Proje genel görünüm** => https://prnt.sc/n0lz39fgHI92

**Notlar**

**Not 1**: mvn test -PBddTest komutu ile projeyi çalıştırdığımızda, test case'lerin sonucuna .reports dizini altından rapor oalrak görüntüleyebiliriz. Ayrıca hata alındığında ekran görüntüsü yine bu dizin altında tutuluyor.
**Not 2**: Ödevde, full content sayfasında tarayıcı başlığı ile haber başlığı AYNI olduğu kontrol edilmeli dendiği için, EQUALS() methodunu kullandım. Fakat case fail oldu çünkü tarayıcı başlığında fazladan "| TechCrunch" texti var. 
Step'in passed olması ve diğer step'inde çalışması için contains() methodu kullandım.
**Not 3**: Proje'de full content sayfasında , news içeriğinde, broken link var mı kontrolü istendiği için ve seçilen news'in içerisinde fazla sayıda link bulunabileceği için, performansı düşünerek multi-threading kullandım. 
Dışardan değer gönderdim. TestNews.class dosyasında görebilirsiniz.
