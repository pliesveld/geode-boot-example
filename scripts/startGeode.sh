#!/bin/bash

gfsh start locator --name=locator0 \
     --dir=/var/gemfire/work \
     --port=10334 --locators=localhost[10334] \
     --hostname-for-clients=localhost \
     --J=-Dgemfire.enable-cluster-configuration=false,-Dgemfire.use-cluster-configuration=false,-Dgemfire.ALLOW_PERSISTENT_TRANSACTIONS=true,-Dgemfire.Query.VERBOSE=true,-Dgemfire.deploy-working-dir=/var/gemfire/work,-Dgemfire.log-file=/var/gemfire/logs/locator.log,-Dgemfire.statistic-archive-file=/var/gemfire/stats/locator.gfs.gz,-Dgemfire.jmx-manager-hostname-for-clients=100.78.20.104,-Djava.rmi.server.hostname=100.78.20.104,-Xms1g,-Xmx1g,-XX:NewSize=128M,-XX:MaxNewSize=128M,-XX:+UnlockDiagnosticVMOptions,-XX:ParGCCardsPerStrideChunk=4096,-XX:+UseNUMA,-XX:+PerfDisableSharedMem,-XX:+UseCompressedOops,-XX:+DisableExplicitGC,-XX:+UseParNewGC,-XX:+UseConcMarkSweepGC,-XX:+UseCMSInitiatingOccupancyOnly,-XX:CMSInitiatingOccupancyFraction=50,-XX:+CMSClassUnloadingEnabled,-XX:+CMSScavengeBeforeRemark,-XX:+PrintGCDetails,-XX:+PrintGCDateStamps,-XX:+PrintGCApplicationStoppedTime,-XX:+PrintGCApplicationConcurrentTime,-XX:+PrintTenuringDistribution,-XX:+PrintGCTimeStamps,-XX:+PrintCompressedOopsMode,-verbose:gc,-Xloggc:/var/gemfire/logs/gc.log,-XX:+UseGCLogFileRotation,-XX:NumberOfGCLogFiles=10,-XX:GCLogFileSize=50M


#     --properties-file=/etc/gemfire/defaults/gemfire.properties \

gfsh start server --log-level="INFO" --name=server0 --server-port=40411 --locators=localhost[10334] --max-threads=3
#gfsh start server --log-level="INFO" --name=server1 --server-port=40412 --locators=localhost[10334] --max-threads=3
#gfsh start server --log-level="INFO" --name=server2 --server-port=40413 --locators=localhost[10334] --max-threads=3

gfsh -e "connect" -e "destroy region --name=/AppointmentSlots --if-exists=true"
gfsh -e "connect --locator=localhost[10334]" -e "create region --name=AppointmentSlots --if-not-exists --eviction-action=local-destroy --region-idle-time-expiration=500 --region-idle-time-expiration-action=DESTROY --enable-statistics=true --type=LOCAL_HEAP_LRU"
gfsh -e "connect --locator=localhost[10334]" -e "create region --name=test --if-not-exists --eviction-action=local-destroy --region-idle-time-expiration=500 --region-idle-time-expiration-action=DESTROY --enable-statistics=true --type=LOCAL_HEAP_LRU"

