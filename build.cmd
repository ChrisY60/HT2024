@echo off
rem This script is used to build the project
call mvnw clean package -DskipTests
call docker-compose build
call docker-compose up -d
