#!/bin/bash

# LiteRead Build Script
# Usage: ./build.sh [debug|release] [clean]

set -e

echo "🚀 LiteRead Build Script"
echo "========================"

# Default values
BUILD_TYPE=${1:-debug}
CLEAN=${2:-}

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Check if gradlew exists
if [ ! -f "gradlew" ]; then
    echo -e "${RED}❌ gradlew not found. Make sure you're in the project root.${NC}"
    exit 1
fi

chmod +x gradlew

# Clean if requested
if [ "$CLEAN" = "clean" ]; then
    echo -e "${YELLOW}🧹 Cleaning build artifacts...${NC}"
    ./gradlew clean
fi

echo -e "${YELLOW}📦 Building $BUILD_TYPE version...${NC}"

case $BUILD_TYPE in
    debug)
        echo -e "${YELLOW}📱 Debug Build${NC}"
        ./gradlew assembleDebug -x lint
        echo -e "${GREEN}✅ Debug APK built successfully${NC}"
        echo "📍 Output: app/build/outputs/apk/debug/"
        ;;
    
    release)
        echo -e "${YELLOW}🔒 Release Build${NC}"
        
        # Check for keystore
        if [ -z "$KEYSTORE_PATH" ]; then
            echo -e "${YELLOW}⚠️  KEYSTORE_PATH not set. Building unsigned.${NC}"
        else
            echo -e "${GREEN}✓ Using keystore: $KEYSTORE_PATH${NC}"
        fi
        
        ./gradlew assembleRelease bundleRelease -x lint
        echo -e "${GREEN}✅ Release build completed${NC}"
        echo "📍 Output: app/build/outputs/apk/release/"
        echo "📍 Output: app/build/outputs/bundle/release/"
        ;;
    
    *)
        echo -e "${RED}❌ Invalid build type. Use 'debug' or 'release'${NC}"
        exit 1
        ;;
esac

# Show build results
echo ""
echo -e "${GREEN}📊 Build Summary${NC}"
echo "=================="

if [ "$BUILD_TYPE" = "debug" ]; then
    if [ -f "app/build/outputs/apk/debug/app-debug.apk" ]; then
        SIZE=$(du -h "app/build/outputs/apk/debug/app-debug.apk" | cut -f1)
        echo "APK Size: $SIZE"
        echo "✅ Build successful!"
    fi
elif [ "$BUILD_TYPE" = "release" ]; then
    if [ -f "app/build/outputs/apk/release/app-release.apk" ]; then
        SIZE=$(du -h "app/build/outputs/apk/release/app-release.apk" | cut -f1)
        echo "APK Size: $SIZE"
        echo "✅ Build successful!"
    fi
fi

echo ""
echo "📋 Next steps:"
echo "1. Run tests: ./gradlew test"
echo "2. Run lint: ./gradlew lint"
echo "3. Install APK: adb install app/build/outputs/apk/$BUILD_TYPE/*-$BUILD_TYPE.apk"
