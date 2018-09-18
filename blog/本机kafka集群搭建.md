# 本地kafka集群搭建
## zookeeper集群的搭建
   以docker方式搭建，创建docker-compose.yml文件，内容如下，然后运行**docker-compose up**
   
   ```yaml
    version: '2'
	services:
    zoo1:
        image: zookeeper
        restart: always
        container_name: zoo1
        ports:
            - "2181:2181"
        environment:
            ZOO_MY_ID: 1
            ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888

    zoo2:
        image: zookeeper
        restart: always
        container_name: zoo2
        ports:
            - "2182:2181"
        environment:
            ZOO_MY_ID: 2
            ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888

    zoo3:
        image: zookeeper
        restart: always
        container_name: zoo3
        ports:
            - "2183:2181"
        environment:
            ZOO_MY_ID: 3
            ZOO_SERVERS: server.1=zoo1:2888:3888 server.2=zoo2:2888:3888 server.3=zoo3:2888:3888
   ```
  
## kafka集群的搭建
1. 去官网下载kafka，地址：http://kafka.apache.org/downloads
2. 下载完成后解压，为了方便，将config/server.properties拷贝到bin目录下，并且复制两份，分别改名为server-1.properties和server-2.properties
3. 修改或添加属性(host.name换成本机ip，ifconfig或者ipconfig查看即可)
   - server.propertie

	   ```properties
	   broker.id=0
		port=9093
		host.name=192.168.0.103	
		log.dirs=/Users/jcala/work/logs/kafka
		zookeeper.connect=localhost:2181,localhost:2182,localhost:2183
	   ```
   - server-1.propertie

      ```properties
	   broker.id=1
		port=9094
		host.name=192.168.0.103	
		log.dirs=/Users/jcala/work/logs/kafka-1
		zookeeper.connect=localhost:2181,localhost:2182,localhost:2183
	   ```
   - server-2.propertie

       ```properties
	   broker.id=0
		port=9093
		host.name=192.168.0.103	
		log.dirs=/Users/jcala/work/logs/kafka
		zookeeper.connect=localhost:2181,localhost:2182,localhost:2183
	   ```
4. 执行**kafka-server-start.sh server.properties**和**kafka-server-start.sh server-1.properties**和**kafka-server-start.sh server-2.properties**启动