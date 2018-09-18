	Date:	2018年8月12日 GMT+8 下午11:06:36

# K8S

### master

k8s集群上两种管理角色: `master` 和 `node` 。每个集群都需要有一个 `master` 节点来管理和控制集群，一个节点可以既是 `master` 节点也可以是 `node` 节点。Master节点上面主要由四个模块组成：​*apiServer*​、​*scheduler*​、​*controller manager*​、​*etcd*​。
​**apiServer**​：负责对外提供RESTful的Kubernetes API服务，它是系统管理指令的统一入口，任何对资源进行增删改查的操作都要交给APIServer处理后再提交给etcd。
​**controller manager**​：每个资源一般都对应有一个控制器，而controller manager就是负责管理这些控制器的，为所有资源的自动化控制中心。
​**schedule**​：负责调度pod到合适的Node上。k8s提供了调度算法，同样也保留了接口，用户可以根据自己的需求定义自己的调度算法。
​**etcd**​：k8s里所有的资源数据保存在etcd中。

### node

每个node节点主要由三个模块组成：​*kubelet*​、​*kube\-proxy*​、​*runtime*​。
​**kubelet**​：负责pod对应的容器的创建，启动等任务，同时与master节点紧密协作，实现集群管理的基本功能。
​**kube\-proxy**​：实现k8s service的通信和负载均衡的重要组件。
​**runtime**​:  容器运行环境，目前Kubernetes支持docker和rkt两种容器。负责本机的容器创建和管理工作。

### pod

pod是k8s的最小工作单元。每个Pod包含一个或多个容器。pod的容器会作为一个整体被master调度刀一个node上运行。引入pod的目的：
​**可管理性**​：有些容器需要紧密联系，一起工作。
​**通信和资源共享**​：一个pod内所有容器使用同一个ip和port，可以直接通过localhost进行通信。也可以共享存储。

### controller

k8s通常不会直接创建pod，而是通过controller管理pod。
​**Depoyment**​：Deployment在继承Pod和Replicaset的所有特性的同时, 它可以实现对template模板进行实时滚动更新并具备我们线上的Application life circle的特性。

​**Replicaset**​：ReplicaSet是下一代复本控制器。ReplicaSet和 Replication Controller之间的唯一区别是现在的选择器支持。Replication Controller只支持基于等式的selector（env=dev或environment\!=qa），但ReplicaSet还支持新的，​*基于集合的selector*​（version in \(v1\.0, v2\.0\)或env notin \(dev, qa\)）。虽然ReplicaSets可以独立使用，但是今天它主要被 Deployments 作为协调pod创建，删除和更新的机制。当您使用Deployments时，您不必担心管理他们创建的ReplicaSets。Deployments拥有并管理其ReplicaSets。

​**DaemonSet**​：DaemonSet保证在每个Node上都运行一个容器副本，常用来部署一些集群的日志、监控或者其他系统管理应用。典型的应用包括​*日志收集*​，比如fluentd，logstash等；​*系统监控*​，比如Prometheus Node Exporter，collectd，New Relic agent，Ganglia gmond等；​*系统程序*​，比如kube\-proxy, kube\-dns, glusterd, ceph等。

​**StatefulSet**​： 为了解决有状态服务的问题。其应用场景包括​*稳定的持久化存储*​，即Pod重新调度后还是能访问到相同的持久化数据，基于PVC来实现；​*稳定的网络标志*​，即Pod重新调度后其PodName和HostName不变，基于Headless Service（即没有Cluster IP的Service）来实现；​*有序部署，有序扩展*​，即Pod是有顺序的，在部署或者扩展的时候要依据定义的顺序依次依次进行（即从0到N\-1，在下一个Pod运行之前所有之前的Pod必须都是Running和Ready状态），基于init containers来实现；​*有序收缩，有序删除*​（即从N\-1到0）。

​**Job**​：负责批量处理短暂的一次性任务 \(short lived one\-off tasks\)，即仅执行一次的任务，它保证批处理任务的一个或多个Pod成功结束。支持一次性Job、固定结束次数的Job、固定结束次数的并行Job、并行Job。

​**CronJob**​：定时任务，就类似于Linux系统的crontab，在指定的时间周期运行指定的任务。在Kubernetes 1\.5，使用CronJob需要开启batch/v2alpha1 API，即–runtime\-config=batch/v2alpha1。

### 其他名称解释

​**Service**​：定义了一个服务的访问入口地址，前端应用通过这个入口地址访问其背后的一组Pod副本组成的集群实例；service和背后的Pod副本集群间通过Label Selector实现对接。

​**Namespace**​：Namespace是对一组资源和对象的抽象集合，比如可以用来将系统内部的对象划分为不同的项目组或用户组。常见的pods, services, replication controllers和deployments等都是属于某一个namespace的（默认是default），而node, persistentVolumes等则不属于任何namespace。

​**Labels 和 Selectors**​：Labels其实就一对 key/value ，被关联到对象上，标签的使用我们倾向于能够标示对象的特殊特点，并且对用户而言是有意义的。API目前支持两种选择器：equality\-based（基于平等）和set\-based（基于集合）的。标签选择器可以由逗号分隔的多个requirements 组成。在多重需求的情况下，必须满足所有要求，因此逗号分隔符作为AND逻辑运算符。

​**Secret**​：解决密码、token、密钥等敏感数据的配置问题，而不需要把这些敏感数据暴露到镜像或者Pod Spec中。Secret可以以Volume或者环境变量的方式使用。Secret有三种类型：
\- Service Account：用来访问Kubernetes API，由Kubernetes自动创建，并且会自动挂载到Pod。
\- Opaque：base64编码格式的Secret，用来存储密码、密钥等。
\- kubernetes\.io/dockerconfigjson：用来存储私有docker registry的认证信息。

​**Volumes**​：第一，当一个容器损坏之后，kubelet 会重启这个容器，但是​*文件会丢失*​。第二，当很多容器在同一Pod中运行的时候，很多时候需要数据​*文件的共享*​。Kubernete Volume解决了这个问题。支持emptyDir、hostPath、gcePersistentDisk、awsElasticBlockStore、nfs、iscsi、glusterfs、rbd、gitRepo、secret、persistentVolumeClaim。

​**PV/PVC/StorageClass**​：PersistentVolume（PV）是集群中已由管理员配置的一段​*网络存储*​。PersistentVolumeClaim（PVC）是​*用户存储的请求*​，它类似于pod， Pod消耗节点资源，PVC消耗光伏资源。StorageClass为管理员提供了一种描述他们提供的存储的“类”的方法。 不同的类可能映射到服务质量级别，或备份策略，或者由群集管理员确定的任意策略。

​**Service Account**​：为了方便​*Pod里面的进程调用Kubernetes API或其他外部服务而设计的*​；仅局限它所在的namespace；每个namespace都会自动创建一个default service account；Token controller检测service account的创建，并为它们创建secret；开启ServiceAccount Admission Controller后：
\- 每个Pod在创建后都会自动设置spec\.serviceAccount为default（除非指定了其他ServiceAccout）。
\- 验证Pod引用的service account已经存在，否则拒绝创建。
\- 如果Pod没有指定ImagePullSecrets，则把service account的ImagePullSecrets加到Pod中。
\- 每个container启动后都会挂载该service account的token和ca\.crt到/var/run/secrets/kubernetes\.io/serviceaccount/

​**Security Context**​：目的是限​*制不可信容器的行为*​，保护系统和其他容器不受其影响。Kubernetes提供了三种配置Security Context的方法：
\- Container\-level Security Context：仅应用到指定的容器
\- Pod\-level Security Context：应用到Pod内所有容器以及Volume
\- Pod Security Policies（PSP）：应用到集群内部所有Pod以及Volume

​**Resource Quotas**​：​*资源配额*​是用来限制用户资源用量的一种机制。资源配额应用在Namespace上，并且每个Namespace最多只能有一个ResourceQuota对象。开启计算资源配额后，创建容器时必须配置计算资源请求或限制（也可以用LimitRange设置默认值）。用户超额后禁止创建新的资源。资源配额的类型包括：计算资源，包括cpu和memory；存储资源，包括存储资源的总量以及指定storage class的总量；对象数，即可创建的对象的个数。

​**Network Policy**​：提供了​*基于策略的网络控制*​，用于隔离应用并减少攻击面。它使用标签选择器模拟传统的分段网络，并通过策略控制它们之间的流量以及来自外部的流量。​*Namespace隔离策略*​：默认情况下，所有Pod之间是全通的。每个Namespace可以配置独立的网络策略，来隔离Pod之间的流量。比如隔离namespace的所有Pod之间的流量。​*Pod隔离策略*​：通过使用标签选择器（包括namespaceSelector和podSelector）来控制Pod之间的流量。

​**Ingress**​：通常情况下，service和pod的IP仅可在集群内部访问。集群外部的请求需要通过负载均衡转发到service在Node上暴露的NodePort上，然后再由kube\-proxy将其转发给相关的Pod，而Ingress就是为进入集群的请求提供路由规则的集合。Ingress可以给service提供集群外部访问的URL、负载均衡、SSL终止、HTTP路由等。为了配置这些Ingress规则，集群管理员需要部署一个Ingress controller，它监听Ingress和service的变化，并根据规则配置负载均衡并提供访问入口。

​**ThirdPartyResources**​：是一种无需改变代码就可以扩展Kubernetes API的机制。

​**ConfigMap**​：用于​*保存配置数据*​的键值对，可以用来保存单个属性，也可以用来保存配置文件。ConfigMap跟secret很类似，但它可以更方便地处理不包含敏感信息的字符串。可以使用kubectl create configmap从文件、目录或者key\-value字符串创建等创建ConfigMap。

​**PodPreset**​：用来给指定标签的Pod注入额外的信息，如环境变量、存储卷等。这样，Pod模板就不需要为每个Pod都显式设置重复的信息。

## 安装

- [mac](https://gist.github.com/kevin-smets/b91a34cea662d0c523968472a81788f7)- 
