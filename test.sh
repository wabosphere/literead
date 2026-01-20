#!/bin/bash

# LiteRead Test Script
# Run all tests and generate reports

set -e

echo "🧪 LiteRead Test Suite"
echo "======================"

chmod +x gradlew

# Colors
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

# Unit Tests
echo -e "${BLUE}1️⃣  Running Unit Tests...${NC}"
./gradlew test || {
    echo -e "${RED}❌ Unit tests failed${NC}"
    exit 1
}
echo -e "${GREEN}✅ Unit tests passed${NC}"

# Lint
echo ""
echo -e "${BLUE}2️⃣  Running Lint...${NC}"
./gradlew lint || {
    echo -e "${YELLOW}⚠️  Lint issues found (see report)${NC}"
}

# Detekt (static analysis)
echo ""
echo -e "${BLUE}3️⃣  Running Detekt...${NC}"
./gradlew detekt || true
echo -e "${GREEN}✅ Detekt analysis completed${NC}"

# KtLint
echo ""
echo -e "${BLUE}4️⃣  Checking Code Style (ktlint)...${NC}"
./gradlew ktlintCheck || {
    echo -e "${YELLOW}⚠️  Code style issues found${NC}"
    echo "Run: ./gradlew ktlintFormat to fix automatically"
}

# Build Debug APK (to check compilation)
echo ""
echo -e "${BLUE}5️⃣  Building Debug APK...${NC}"
./gradlew assembleDebug -x lint
echo -e "${GREEN}✅ Debug APK built successfully${NC}"

# Generate Reports
echo ""
echo -e "${BLUE}📊 Generating Reports...${NC}"
mkdir -p build/reports

if [ -f "app/build/reports/tests/testDebugUnitTest/index.html" ]; then
    echo -e "${GREEN}✅ Unit test report: app/build/reports/tests/testDebugUnitTest/index.html${NC}"
fi

if [ -f "app/build/reports/lint-results-debug.html" ]; then
    echo -e "${GREEN}✅ Lint report: app/build/reports/lint-results-debug.html${NC}"
fi

echo ""
echo -e "${GREEN}✅ All tests completed!${NC}"
echo ""
echo "📋 Reports:"
echo "- Unit Tests: app/build/reports/tests/"
echo "- Lint: app/build/reports/lint-results*.html"
echo "- Detekt: app/build/reports/detekt/"
