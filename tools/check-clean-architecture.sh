#!/usr/bin/env bash

readonly SRC_PATH="src/main/java/com/example/demo"

readonly DOMAIN_PACKAGE_PATH="${SRC_PATH}/domain"
readonly USE_CASES_PACKAGE_PATH="${SRC_PATH}/use_cases"

readonly UNAUTHORIZED_PACKAGES_USAGE_IN_USE_CASES="infrastructure|hibernate"
readonly UNAUTHORIZED_PACKAGES_USAGE_IN_DOMAIN="${UNAUTHORIZED_PACKAGES_USAGE_IN_USE_CASES}|use_cases|springframework|javax"

readonly UNAUTHORIZED_PACKAGES_USAGE_COUNT_IN_DOMAIN=$(find ${DOMAIN_PACKAGE_PATH} -name "*.java" -exec egrep -w ${UNAUTHORIZED_PACKAGES_USAGE_IN_DOMAIN} {} \; | wc -l)
readonly UNAUTHORIZED_PACKAGES_USAGE_COUNT_IN_USE_CASES=$(find ${USE_CASES_PACKAGE_PATH} -name "*.java" -exec egrep -w ${UNAUTHORIZED_PACKAGES_USAGE_IN_USE_CASES} {} \; | wc -l)

if [[ "${UNAUTHORIZED_PACKAGES_USAGE_COUNT_IN_DOMAIN}" -eq 0 ]] && [[ "${UNAUTHORIZED_PACKAGES_USAGE_COUNT_IN_USE_CASES}" -eq 0 ]]; then
  exit 0
fi

echo "${UNAUTHORIZED_PACKAGES_USAGE_COUNT_IN_DOMAIN} unauthorized packages in ${DOMAIN_PACKAGE_PATH}:"
find ${DOMAIN_PACKAGE_PATH} -name "*.java" -exec egrep -lw ${UNAUTHORIZED_PACKAGES_USAGE_IN_DOMAIN} {} \;
echo ""
echo "${UNAUTHORIZED_PACKAGES_USAGE_COUNT_IN_USE_CASES} unauthorized packages in ${USE_CASES_PACKAGE_PATH}:"
find ${USE_CASES_PACKAGE_PATH} -name "*.java" -exec egrep -lw ${UNAUTHORIZED_PACKAGES_USAGE_IN_USE_CASES} {} \;
exit 1
