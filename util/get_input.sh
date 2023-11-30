#!/bin/sh

YEAR="2023"
DAY=`date "+%d"`
AUTH="Cookie: session=GA1.2.674011021.1676992847; session=53616c7465645f5fd6d9143ef92e98270a8abe28de13656455d85f7d809f7cd1cc53ab33b701d37f8ddf560a75518aaf7d42bd246b95af8d22dcdef2caf52771"

curl -H "$AUTH" -o day$DAY.txt https://adventofcode.com/$YEAR/day/$DAY/input
