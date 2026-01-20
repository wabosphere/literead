#!/usr/bin/env bash

# LiteRead Installation & Setup Script
# One-command setup for development

set -e

echo "📱 LiteRead Development Setup"
echo "============================="

# Colors
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
RED='\033[0;31m'
NC='\033[0m'

# Check requirements
echo -e "${BLUE}Checking requirements...${NC}"

# Check Java
if ! command -v java &> /dev/null; then
    echo -e "${RED}❌ Java not found. Please install JDK 11 or higher${NC}"
    exit 1
fi
JAVA_VERSION=$(java -version 2>&1 | grep -oP 'version "\K[^"]+' | cut -d. -f1)
if [ "$JAVA_VERSION" -lt 11 ]; then
    echo -e "${RED}❌ Java 11+ required (found: $JAVA_VERSION)${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Java $JAVA_VERSION found${NC}"

# Check Android SDK
if [ -z "$ANDROID_HOME" ]; then
    echo -e "${YELLOW}⚠️  ANDROID_HOME not set. Checking common locations...${NC}"
    if [ -d "$HOME/Android/Sdk" ]; then
        export ANDROID_HOME="$HOME/Android/Sdk"
        echo -e "${GREEN}✓ Found Android SDK at: $ANDROID_HOME${NC}"
    else
        echo -e "${RED}❌ Android SDK not found. Install Android Studio and set ANDROID_HOME${NC}"
        exit 1
    fi
else
    echo -e "${GREEN}✓ Android SDK found at: $ANDROID_HOME${NC}"
fi

# Check git
if ! command -v git &> /dev/null; then
    echo -e "${RED}❌ Git not found. Please install git${NC}"
    exit 1
fi
echo -e "${GREEN}✓ Git found${NC}"

echo ""
echo -e "${BLUE}Setting up LiteRead project...${NC}"

# Make scripts executable
chmod +x gradlew build.sh test.sh format.sh

# Build gradle wrapper
./gradlew --version > /dev/null

echo ""
echo -e "${BLUE}Building project...${NC}"
./gradlew build --stacktrace 2>&1 | tail -20

echo ""
echo -e "${GREEN}✅ Setup completed!${NC}"

echo ""
echo "📋 Quick Start Guide:"
echo "===================="
echo "1. Open in Android Studio:"
echo "   - Open Android Studio"
echo "   - File → Open"
echo "   - Select LiteRead project root"
echo ""
echo "2. Build & Run:"
echo "   ./gradlew assembleDebug     # Build APK"
echo "   ./gradlew test              # Run tests"
echo "   adb install app/build/outputs/apk/debug/*.apk  # Install"
echo ""
echo "3. Format Code:"
echo "   ./format.sh                 # Format & check style"
echo ""
echo "4. Run All Tests:"
echo "   ./test.sh                   # Run all tests"
echo ""
echo "5. Create Release Build:"
echo "   ./build.sh release          # Build release APK"
echo ""
echo "📚 Documentation:"
echo "- README.md - Project overview"
echo "- DOCUMENTATION.md - Full documentation"
echo "- DEVELOPMENT.md - Development guide"
echo "- CONTRIBUTING.md - Contribution guidelines"
echo ""
echo "🔗 Useful Links:"
echo "- GitHub: https://github.com/wabosphere/literead"
echo "- Android Dev: https://developer.android.com"
echo "- Kotlin: https://kotlinlang.org"
echo ""
echo -e "${GREEN}Happy coding! 🚀${NC}"
