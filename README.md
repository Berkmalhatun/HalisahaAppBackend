
# Halisaha Kiralama Sistemi

Java tabanlı bir mikroservis mimarisi geliştirerek futbol sahası kiralama sistemi üzerinde çalıştım. Bu sistem, kullanıcıların futbol sahalarını kolayca kiralamalarını sağlayan kapsamlı bir platformdur. Projenin temelinde, kullanıcıların veri tabanıyla etkileşimini sağlayan CRUD (oluşturma, güncelleme, silme ve okuma) işlemlerini başarıyla gerçekleştirdim.

Sistemde, kullanıcılara özgü rol atamaları yaptım ve giriş sonrası token bazlı rol doğrulaması ile güvenlik sağladım. Bu, kullanıcıların yetkilerini etkin bir şekilde yönetmemizi sağladı. Özellikle, kullanıcılara bölgesel olarak uygun futbol sahalarını listeleme özelliği ekledim, bu sayede kullanıcılar ihtiyaçlarına en uygun sahayı kolayca bulabildiler.

Projede ayrıca, 'Halısaha Yöneticisi' rolüne sahip kullanıcılar için özel işlevler geliştirdim. Bu kullanıcılar, kendi sahalarını sisteme ekleyebilir, düzenleyebilir

ve kiralama işlemlerini yönetebilirler. Ayrıca, kiralanmış sahaları kullanan kişilerin bilgilerini görüntüleme yetkilerine sahiptirler. Bu özellik, halı saha yöneticilerine sahalarını daha etkin bir şekilde yönetme imkanı tanıdı.

Sistemin bir diğer önemli özelliği ise, misafir kullanıcı işlevselliğidir. Sistem dışından yapılan kiralama işlemleri için, misafir kullanıcılar olarak sisteme ekleme yapılabilir. Bu sayede, platform dışından gerçekleşen işlemler de kayıt altına alınarak, işlemlerin şeffaflığı ve izlenebilirliği artırıldı.

Projenin teknoloji yelpazesi oldukça geniştir. Java, Spring Boot ve Spring Security başta olmak üzere, Lombok, Mapstruct, OpenAPI, Swagger UI, OpenFeign, RabbitMQ, MongoDB, JWT ve React gibi teknolojileri kullanarak, güçlü ve esnek bir sistem inşa ettim. Bu proje, modern web geliştirme pratiklerine olan hakimiyetimi ve çeşitli araçları entegre etme yeteneğimi göstermektedir.

 ## Proje Nasıl Klonlanır?

1. Bilgisayarınızda Git'in yüklü olması gerekmektedir.

2. Komut istemcisini açın.

3. Projeyi klonlamak istediğiniz dizine gidin, aşağıdaki komutu istemcinize yazın.


```bash 
git clone https://github.com/Berkmalhatun/HalisahaAppBackend
```
4. Kullanmış olduğunuz IDE'yi açın  
5. Klonlamış olduğunuz dosyayı IDE üzerinden açın.
6. Dosya açıldığında bağımlılıklar yüklenene kadar bekleyin.
7. Docker da rabbitmq ve mongodb kurulumlarını yapınız.
8. Her bir mikroservisin'in application'ına sağ tıklayıp Run diyerek çalıştırabilirsiniz. Eğer docker işlemleri yapılmadıysa proje ayağa kaldırılamaz.
## Docker entegrasyonu
1. Docker'da mongodb kurulur.
  ```bash 
 docker run --name halisahadb -e MONGO_INITDB_ROOT_USERNAME=mongo -e MONGO_INITDB_ROOT_PASSWORD=root -p 27021:27017 -d mongo
bck-i-search: mongo_
 ```
2. Docker'da rabbitmq kurulur.
 ```bash 
 docker run -d --name rabbitmq -p 15672:15672 -p 5672:5672 -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=root rabbitmq:management
  ```
## Test işlemleri
 Test işlemleri için proje ayağa kaldırıldıktan sonra aşağıdaki linklerden 3 mikroservis içinde test işlemleri yapılabilir.
  ```bash 
http://localhost:4040/swagger-ui/index.html
  ```
   ```bash 
http://localhost:4041/swagger-ui/index.html
  ```
   ```bash 
http://localhost:4042/swagger-ui/index.html
  ```

## Ekran Görüntüleri
### Auth-Controller
[![image](https://i.hizliresim.com/a2jtl5i.PNG)](https://hizliresim.com/a2jtl5i)
### User-Controller
[![image](https://i.hizliresim.com/uen43l7.PNG)](https://hizliresim.com/uen43l7)
### Football-Field-Controller
[![image](https://i.hizliresim.com/3stnf0r.PNG)](https://hizliresim.com/3stnf0r)
### Rent-Football-Field-Controller
[![image](https://i.hizliresim.com/3jvvc7k.PNG)](https://hizliresim.com/3jvvc7k)
### City-Controller
[![image](https://i.hizliresim.com/e3meqlk.PNG)](https://hizliresim.com/e3meqlk)
## Son
İnceleyip zaman ayırdığınız için teşekkür ederim.
