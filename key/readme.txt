１．キー作成手順
C:\workspace\MasaClock\key>"C:\Program Files\Java\jdk1.6.0_23\bin\keytool" -genkey -keystore masaclock.keystore -alias masaclock -validity 10000

 キーストアのパスワードを入力してください:masaclock
新規パスワードを再入力してください:masaclock
姓名を入力してください。
  [Unknown]:  Tatsumasa Kuwabara
組織単位名を入力してください。
  [Unknown]:
組織名を入力してください。
  [Unknown]:  Masa Project
都市名または地域名を入力してください。
  [Unknown]:  Gifu
州名または地方名を入力してください。
  [Unknown]:  Gifu
この単位に該当する 2 文字の国番号を入力してください。
  [Unknown]:  jp
CN=Tatsumasa Kuwabara, OU=Unknown, O=Masa Project, L=Gifu, ST=Gifu, C=jp でよろ
しいですか?
  [no]:  yes

<masaclock> の鍵パスワードを入力してください。
        (キーストアのパスワードと同じ場合は RETURN を押してください):

２．署名手順
C:\workspace\MasaClock\key>cd ..
C:\workspace\MasaClock>cd \workspace\MasaClock

C:\workspace\MasaClock>"C:\Program Files\Java\jdk1.6.0_23\bin\jarsigner.exe" -verbose -keystore key\masaclock.keystore bin\MasaClock.apk masaclock
キーストアのパスワードを入力してください:
   追加中: META-INF/MASACLOC.SF
   追加中: META-INF/MASACLOC.DSA
  署名中: res/drawable/digital_clock.png
  署名中: res/drawable/icon.png
  署名中: res/layout/main.xml
  署名中: res/raw/s1.ogg
  署名中: res/raw/s2.ogg
  署名中: AndroidManifest.xml
  署名中: resources.arsc
  署名中: classes.dex

C:\workspace\MasaClock>C:\android-sdk-windows\tools\zipalign.exe -f -v 4 bin\MasaClock.apk bin\MasaClock_zipalign.apk
Verifying alignment of bin\MasaClock_zipalign.apk (4)...
      50 META-INF/MANIFEST.MF (OK - compressed)
     492 META-INF/MASACLOC.SF (OK - compressed)
    1034 META-INF/MASACLOC.DSA (OK - compressed)
    1872 META-INF/CERT.SF (OK - compressed)
    2359 META-INF/CERT.RSA (OK - compressed)
    3044 res/drawable/digital_clock.png (OK)
    3792 res/drawable/icon.png (OK)
    6044 res/drawable/large.png (OK)
   11204 res/layout/main.xml (OK - compressed)
   11564 res/raw/s1.ogg (OK)
   16416 res/raw/s2.ogg (OK)
   33317 AndroidManifest.xml (OK - compressed)
   33952 resources.arsc (OK)
   35825 classes.dex (OK - compressed)
Verification succesful
