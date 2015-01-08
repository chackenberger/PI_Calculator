#!/bin/bash
mvn package -P Client
mvn package -P Server
mvn package -P Balancer
