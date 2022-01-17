# Password storage

Для забезпечення безпеки інформації, що зберігається про користувача в базі даних, було застосовано алгоритм AES.
Щоб зашифрувати особисті дані користувачів (ПІБ, номер телефону, електронну пошту), необхідно:
1) сформувати SecretKey з паролю та солі, але т. я. пароль також зберігається в базі даних, і доволі легко передбачити,
що для SecretKey буде застосовуватись саме пароль, як "пароль" для кожного поля було придумано свій пароль, який надійно
зберігається в config-файлі з закритим доступом. Сіль же зберігається в базі даних в таблиці з паролем;
2) шифрування plain text відбувається алгоритмом PBKDF2WithHmacSHA256 за допомогою того самого SecretKey, а також вектору IV,
який захищаэ дані від plain text attack. Цей вектор формується для кожного запису таблиці та зберігається в хмарі, але
т. я. ми лише навчаємось, я зробила простішу реалізацію та просто зберігаю вектор в тій самій базі даних;
3) зашифровані дані надсилаються до таблиці.

# TLS configuration

Для налаштування TLS в Spring Boot проекті, необхідно здійснити наступні кроки:

1) для формування keystore, тобто self-signed сертифікату, було використано keytool, наданий java.
Необхідно виконати наступну команду:
keytool -genkeypair -alias lab7 -keyalg RSA -keysize 4096 -validity 3650 -dname "CN=localhost" -keypass plsWork 
-keystore keystore.p12 -storeType PKCS12 -storepass plsWork
Таким чином було сформовано сертифікат. Файл keystore.p12 кладеться в папку resources.
2) application.properties мають бути налаштовані наступним чином:
server.ssl.enabled=true
server.ssl.key-store=src/main/resources/final-keystore.p12
server.ssl.key-store-password=plsWork
server.ssl.key-store-type=PKCS12
server.ssl.key-alias=final-keystore
server.ssl.ciphers=TLS_AES_128_GCM_SHA256
Як cipherSuit було обрано TLS_AES_128_GCM_SHA256.
Також необхідно здійснити конфігурацію для серверу Tomcat за допомогою наступного біна:
@Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };

        return tomcat;
    }
Після всіх проведених операцій маємо:
 
 
