# git flow使用手册

![git_flow使用手册](../../pics/git_flow使用手册_01.png)

### git flow分支
  1. master分支
  > 这个分支代码为码，最近发布的Release， 这个分支只能从其他分支合并，不能在这个分支直接修改
  2. develop 分支
  > 这个分支是我们是我们的主开发分支，包含所有要发布到下一个Release的代码，这个主要合并与其他分支，比如Feature分支
  3. feature 分支
  > 当你需要一个发布一个新Release的时候，我们基于Develop分支创建一个Release分支，完成Release后，我们合并到Master和Develop分支
  4. hotfix分支
  > 当我们在Production发现新的Bug时候，我们需要创建一个Hotfix, 完成Hotfix后，我们合并回Master和Develop分支，所以Hotfix的改动会进入下一个Release

### windows上git flow安装
  1. ``` git clone --recursive git://github.com/nvie/gitflow.git ```
  2. 以管理员身份打开cmd，切换到gitflow目录下的contrib目录
  3. 执行msysgit-install.cmd git安装目录。例如``` msysgit-install.cmd "C:\Program Files\Git" ```

### 根据默认初始化项目
  ```
  git flow init -d
  ```
  git flow会创建master和develop分支，默认所在develop分支。

### 开发新的功能，例如用户注册
  1. 开发新功能
  ```
  git flow feature start user_register
  ```
  它会创建一个分支feature/user_register，并切换到该分支

  2. 开发完成
  ```
  git flow feature finish user_register
  ```
  将该分支合并到develop分支，然后切换回develop分支，并删除feature/user_register分支

  3. 删除弃用分支
  ```
  git flow feature delete user_register
  ```

  4. 合作开发-推送分支
  如果你想让别人和你一起开发user_register分支，那就把这个分支push到服务器上
  ```
  git flow feature publish user_register
  ```

  5. 合作开发-取得分支，签出远程更新
  ```
  git flow feature pull origin user_register
  ```

  6. 合作开发-追踪在origin上的特性分支
  ```
   git flow feature track user_register
  ```

### 发布分支
 1. 新建发布分支
 当develop分支有了一次发布的足够功能，就可以新建一个发布准备的专门分支。
 ```
 git flow release start 0.1.0
 ```

 2. 此时本分支进行测试等，测试完成发布
 ```
 git flow release finish 0.1.0
 ```
 会将release/0.1.0分支的内容合并到master和develop，并且打上tag 0.1.0，然后删除release/0.1.0分支

 3. 删除弃用分支
 ```
 git flow release delete 0.1.0
 ```

 4. 推送分支
 ```
 git flow release publish 0.1.0
 ```

 5. 追踪在origin上的特性分支
 ```
 git flow release track 0.1.0
 ```

### 紧急修复
 1. 创建hotfix分支
 当用户测试发现bug。
 ```
 git flow hotfix start 0.1.1
 ```
 此时gitflow从master分支上拉出一个hotfix/0.1.1的分支，接下来在新分支上修改bug

 2. 修复bug合并分支
 ```
 git flow hotfix finish 0.1.1
 ```
 这样hotfix/0.1.1被merge到master分支和develop分支，打好0.1.1这个tag，删除这个分支，切换回develop分支。

 3. 删除弃用分支
 ```
 git flow hotfix delete 0.1.1
 ```

 4. 推送分支
 ```
 git flow hotfix publish 0.1.1
 ```
