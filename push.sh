#!/bin/bash
cd /workspaces/literead
git add app/proguard-rules.pro
git commit -m "Fix R8 minification error with XmlPull dependencies"
git push origin main
