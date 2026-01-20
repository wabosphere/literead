#!/bin/bash

# LiteRead Format & Style Check
# Automatically format code and check style

set -e

echo "🎨 LiteRead Code Formatter"
echo "=========================="

chmod +x gradlew

# Colors
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Format with ktlint
echo -e "${BLUE}🔧 Formatting Kotlin code...${NC}"
./gradlew ktlintFormat
echo -e "${GREEN}✅ Code formatted${NC}"

# Check style
echo ""
echo -e "${BLUE}🔍 Checking code style...${NC}"
./gradlew ktlintCheck || {
    echo -e "${YELLOW}⚠️  Some style issues remain${NC}"
}
echo -e "${GREEN}✅ Style check completed${NC}"

# Check with Detekt
echo ""
echo -e "${BLUE}🔎 Running Detekt...${NC}"
./gradlew detekt || true
echo -e "${GREEN}✅ Detekt analysis completed${NC}"

echo ""
echo -e "${GREEN}✨ Code formatting completed!${NC}"
