/*
   -- Remote Controller --
   
   This source code of graphical user interface 
   has been generated automatically by RemoteXY editor.
   To compile this code using RemoteXY library 3.1.13 or later version 
   download by link http://remotexy.com/en/library/
   To connect using RemoteXY mobile app by link http://remotexy.com/en/download/                   
     - for ANDROID 4.15.01 or later version;
     - for iOS 1.12.1 or later version;
    
   This source code is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 2.1 of the License, or (at your option) any later version.    
*/

//////////////////////////////////////////////
//        RemoteXY include library          //
//////////////////////////////////////////////

// you can enable debug logging to Serial at 115200
//#define REMOTEXY__DEBUGLOG    

// RemoteXY select connection mode and include library 
// you can enable debug logging to Serial at 115200
//#define REMOTEXY__DEBUGLOG    

// RemoteXY select connection mode and include library 
#define REMOTEXY_MODE__SOFTSERIAL

#include <SoftwareSerial.h>

// RemoteXY connection settings 
#define REMOTEXY_SERIAL_RX 2
#define REMOTEXY_SERIAL_TX 3
#define REMOTEXY_SERIAL_SPEED 9600
#define LEFT_SENSOR_PIN 11
#define center_SENSOR_PIN 12
#define RIGHT_SENSOR_PIN 13

#include <RemoteXY.h>

// RemoteXY GUI configuration  
#pragma pack(push, 1)  
uint8_t RemoteXY_CONF[] =   // 278 bytes
  { 255,8,0,1,0,15,1,19,0,0,0,0,26,2,200,84,200,92,1,1,
  13,0,1,148,38,21,21,5,36,23,23,0,1,31,76,101,102,116,0,1,
  150,15,21,21,42,36,23,23,0,1,31,82,105,103,104,116,0,1,150,56,
  21,21,23,55,23,23,0,1,31,66,97,99,107,119,97,114,100,0,1,153,
  7,21,21,23,17,23,23,0,1,31,70,111,114,119,97,114,100,0,4,178,
  36,11,33,72,64,65,12,128,1,16,129,156,1,27,11,153,15,34,7,64,
  16,76,105,110,101,32,102,111,108,108,111,119,0,2,90,27,44,20,150,24,
  39,12,1,1,16,16,16,79,78,0,79,70,70,0,1,112,23,22,22,5,
  16,16,16,0,1,31,72,79,82,78,0,129,156,46,30,6,160,51,20,7,
  64,16,76,105,103,104,116,115,0,2,151,54,39,11,150,60,39,12,1,1,
  16,16,16,79,78,0,79,70,70,0,72,90,7,22,22,80,11,48,48,12,
  166,75,1,16,0,0,0,0,0,0,200,66,0,0,0,0,129,127,33,30,
  6,80,4,45,7,64,16,67,117,114,114,101,110,116,32,80,111,119,101,114,
  0,129,126,41,30,6,93,57,23,8,64,16,83,112,101,101,100,0 };
  
// this structure defines all the variables and events of your control interface 
struct {

    // input variables
  uint8_t left; // =1 if button pressed, else =0
  uint8_t right; // =1 if button pressed, else =0
  uint8_t back; // =1 if button pressed, else =0
  uint8_t forward; // =1 if button pressed, else =0
  int8_t power; // from 0 to 100
  uint8_t line_follow; // =1 if switch ON and =0 if OFF
  uint8_t horn; // =1 if button pressed, else =0
  uint8_t leds; // =1 if switch ON and =0 if OFF

    // output variables
  int8_t motor_value; // from 0 to 100

    // other variable
  uint8_t connect_flag;  // =1 if wire connected, else =0

} RemoteXY;   
#pragma pack(pop)
 
/////////////////////////////////////////////
//           END RemoteXY include          //
/////////////////////////////////////////////


#define motmor1_pin1 9
#define motmor1_pin2 8
#define motmor2_pin1 7
#define motmor2_pin2 6
#define enA 10
#define enB 5

int led = 4;
int buzzer = 14;
int M1_Speed = 65;
int M2_Speed = 65;
int LeftRotationSpeed = 85;
int RightRotationSpeed = 85;

void sound(){
  if (RemoteXY.horn == 1){
      digitalWrite(buzzer,HIGH);
    }
  else {
    digitalWrite(buzzer,LOW);
  }
}

void lights(){
  if (RemoteXY.leds == 1){
    digitalWrite(led,HIGH);
  }
  else{
    digitalWrite(led,LOW);
  }
}

void forward() {
  digitalWrite(motmor1_pin1, HIGH);
  digitalWrite(motmor1_pin2, LOW);
  digitalWrite(motmor2_pin1, HIGH);
  digitalWrite(motmor2_pin2, LOW);
  analogWrite(enA, M1_Speed);
  analogWrite(enB, M2_Speed);
}

void backward() {
  digitalWrite(motmor1_pin1, LOW);
  digitalWrite(motmor1_pin2, HIGH);
  digitalWrite(motmor2_pin1, LOW);
  digitalWrite(motmor2_pin2, HIGH);
  analogWrite(enA, M1_Speed);
  analogWrite(enB, M2_Speed);
}

void sharpRight() {
  digitalWrite(motmor1_pin1, HIGH);
  digitalWrite(motmor1_pin2, LOW);
  digitalWrite(motmor2_pin1, LOW);
  digitalWrite(motmor2_pin2, HIGH);
  analogWrite(enA, LeftRotationSpeed);
  analogWrite(enB, M2_Speed);
}

void sharpLeft() {
  digitalWrite(motmor1_pin1, LOW);
  digitalWrite(motmor1_pin2, HIGH);
  digitalWrite(motmor2_pin1, HIGH);
  digitalWrite(motmor2_pin2, LOW);
  analogWrite(enA, M1_Speed);
  analogWrite(enB, RightRotationSpeed);
}

void center() {
  digitalWrite(motmor1_pin1, HIGH);
  digitalWrite(motmor1_pin2, LOW);
  digitalWrite(motmor2_pin1, HIGH);
  digitalWrite(motmor2_pin2, LOW);
  analogWrite(enA, M1_Speed / 1.5);
  analogWrite(enB, M2_Speed / 1.5);
}

void Stop() {
  digitalWrite(motmor1_pin1, LOW);
  digitalWrite(motmor1_pin2, LOW);
  digitalWrite(motmor2_pin1, LOW);
  digitalWrite(motmor2_pin2, LOW);
}

int getStableReading(int pin) {
  int count = 0;
  for (int i = 0; i < 10; i++) {
    count += digitalRead(pin);
    delay(1);
  }
  return (count > 5) ? HIGH : LOW;
}
void linefollow() {

  int LEFT_SENSOR = getStableReading(LEFT_SENSOR_PIN);
  int RIGHT_SENSOR = getStableReading(RIGHT_SENSOR_PIN);
  int center_SENSOR = getStableReading(center_SENSOR_PIN);
  if (RIGHT_SENSOR == LOW && LEFT_SENSOR == LOW && center_SENSOR == HIGH) {
    forward();
  } else if (RIGHT_SENSOR == LOW && LEFT_SENSOR == HIGH && center_SENSOR == HIGH) {
    sharpLeft();
  } else if (RIGHT_SENSOR == HIGH && LEFT_SENSOR == LOW && center_SENSOR == HIGH) {
    sharpRight();
  } else if (RIGHT_SENSOR == HIGH && LEFT_SENSOR == LOW && center_SENSOR == LOW) {
    sharpRight();
  } else if (RIGHT_SENSOR == LOW && LEFT_SENSOR == HIGH && center_SENSOR == LOW) {
    sharpLeft();
  } else if (RIGHT_SENSOR == HIGH && LEFT_SENSOR == HIGH && center_SENSOR == LOW) {
    forward();
  } else if (RIGHT_SENSOR == LOW && LEFT_SENSOR == LOW && center_SENSOR == LOW) {
    Stop();
  } else if (RIGHT_SENSOR == HIGH && LEFT_SENSOR == HIGH && center_SENSOR == HIGH) {
    backward();
  }
}



void setup() 
{
  RemoteXY_Init(); 
  // TODO you setup code
  Serial.begin(9600);
  pinMode(motmor1_pin1, OUTPUT);
  pinMode(motmor1_pin2, OUTPUT);
  pinMode(motmor2_pin1, OUTPUT);
  pinMode(motmor2_pin2, OUTPUT);
  pinMode(enA, OUTPUT);
  pinMode(enB, OUTPUT);
  pinMode(LEFT_SENSOR_PIN, INPUT_PULLUP);
  pinMode(RIGHT_SENSOR_PIN, INPUT_PULLUP);
  pinMode(buzzer,OUTPUT);
  pinMode(led,OUTPUT);
}

void loop() 
{ 
  RemoteXY_Handler ();
  RemoteXY.motor_value = 0;
  sound();
  lights();
    if (RemoteXY.line_follow == 1){
      linefollow();
    }
    else{
      if (RemoteXY.forward == 1){
        RemoteXY.motor_value = (int)RemoteXY.power;
        digitalWrite(motmor1_pin1, 1);
        digitalWrite(motmor1_pin2, 0);

        digitalWrite(motmor2_pin1, 1);
        digitalWrite(motmor2_pin2, 0);
        analogWrite (enA,RemoteXY.power *2.55);
        analogWrite (enB,RemoteXY.power *2.55);
      }
      else if (RemoteXY.back == 1){
        RemoteXY.motor_value = (int)RemoteXY.power;
        digitalWrite(motmor1_pin1, 0);
        digitalWrite(motmor1_pin2, 1);
      
        digitalWrite(motmor2_pin1, 0);
        digitalWrite(motmor2_pin2, 1);
        analogWrite (enA,RemoteXY.power *2.55);
        analogWrite (enB,RemoteXY.power *2.55);
      }
      else if(RemoteXY.right == 1){
        RemoteXY.motor_value = (int)RemoteXY.power;
        digitalWrite(motmor1_pin1, 0);
        digitalWrite(motmor1_pin2, 1);

        digitalWrite(motmor2_pin1, 1);
        digitalWrite(motmor2_pin2, 0); 
        analogWrite (enA,RemoteXY.power *2.55);
        analogWrite (enB,RemoteXY.power *2.55);  
      }
      else if(RemoteXY.left == 1){
        RemoteXY.motor_value = (int)RemoteXY.power;
        digitalWrite(motmor1_pin1, 1);
        digitalWrite(motmor1_pin2, 0);

        digitalWrite(motmor2_pin1, 0);
        digitalWrite(motmor2_pin2, 1);
        analogWrite (enA,RemoteXY.power *2.55);
        analogWrite (enB,RemoteXY.power *2.55);
      }
      else {
        RemoteXY.motor_value = 0;
        digitalWrite(motmor1_pin1, 0);
        digitalWrite(motmor1_pin2, 0);

        digitalWrite(motmor2_pin1, 0);
        digitalWrite(motmor2_pin2, 0);
        analogWrite (enA,RemoteXY.power *2.55);
        analogWrite (enB,RemoteXY.power *2.55);
      }
  // TODO you loop code
  // use the RemoteXY structure for data transfer
  // do not call delay(), use instead RemoteXY_delay() 


    }
}