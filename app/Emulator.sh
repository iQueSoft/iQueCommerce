#!/bin/bash

#Start the emulator
E:\\Android\\Android_SDK\\tools\\emulator -avd Nexus_5X_API_24 &
EMULATOR_PID=$!

# Wait for Android to finish booting
WAIT_CMD="E:\\Android\\Android_SDK\\platform-tools\\adb wait-for-device shell getprop init.svc.bootanim"
until $WAIT_CMD | grep -m 1 stopped; do
  echo "Waiting..."
  sleep 1
done
 

# Clear and capture logcat
E:\\Android\\Android_SDK\\platform-tools\\adb logcat -c
E:\\Android\\Android_SDK\\platform-tools\\adb logcat > build/logcat.log &
LOGCAT_PID=$!
