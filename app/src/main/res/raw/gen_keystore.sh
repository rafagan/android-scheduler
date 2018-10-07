#!/usr/bin/env bash

# Release
keytool -exportcert -list -v -alias scheduler_keystore -keystore /Applications/Android\ Studio.app/Contents/bin/scheduler.keystore

# Debug
# A senha normalmente é android
# keytool -exportcert -list -v -alias androiddebugkey -keystore ~/.android/debug.keystore