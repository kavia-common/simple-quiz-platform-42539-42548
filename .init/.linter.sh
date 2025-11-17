#!/bin/bash
cd /home/kavia/workspace/code-generation/simple-quiz-platform-42539-42548/android_frontend
./gradlew lint
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

