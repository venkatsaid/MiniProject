#include <Firebase.h>
#include <FirebaseArduino.h>
#include <FirebaseObject.h>
#include <FirebaseCloudMessaging.h>
#include <FirebaseError.h>
#include <FirebaseHttpClient.h>

#include "ESP8266WiFi.h"

#define FIREBASE_URL "miniproject-7cdfa.firebaseio.com"
#define FIREBASE_SECRET "liGde2ndKDk9x929kM35JqOUjbh41iZeYAXd8miP"

int RELAY = 4;  //Pin D2
const char* NET_SSID = "Hello";
const char* NET_PASSWORD = "123456789";
void setup() {
  Serial.begin(115200);
    pinMode(RELAY, OUTPUT);
  WiFi.mode(WIFI_STA);
   WiFi.begin(NET_SSID, NET_PASSWORD);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  Serial.println("Setup done");
      
  Firebase.begin(FIREBASE_URL,FIREBASE_SECRET); 
 }

void loop() {
  FirebaseObject obj = Firebase.get("/homeautomation-1103");
  if (Firebase.failed()) {
      Serial.println("Firebase get failed");
      Serial.println(Firebase.error());
      return;
  }
  int i = Firebase.getInt("/MyHome/fan1");
   
  Serial.println(i);
  if( i== 1){
    digitalWrite(RELAY,LOW);
  }
  else{
    digitalWrite(RELAY,HIGH);
    
  }
      
  
}

