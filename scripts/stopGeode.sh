#!/usr/bin/env bash

LOCATOR_ARG="connect --locator=localhost[10334]"

gfsh -e "$LOCATOR_ARG" -e "stop server --name=server0"

gfsh -e "$LOCATOR_ARG" -e "stop server --name=server1"

gfsh -e "$LOCATOR_ARG" -e "stop server --name=server2"

gfsh -e "$LOCATOR_ARG" -e "shutdown --include-locators=true"

