const byte pHpin = A0;
float Po;
void setup() {
  Serial.begin(9600);
}

void loop() {
  Po = 1023 - analogRead(pHpin);   //Read and reverse the analogue input value from the pH sensor.
  Po = Po*14.0/1024; //Convert Analog value into 0 - 14 Scale
  Serial.println(Po); //Print the result      
  delay(1000); // Wait for 1 second                
}

