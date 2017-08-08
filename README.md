
## 资料：

* http://projects.spring.io/spring-amqp/
* https://github.com/spring-projects/spring-amqp-samples/
* http://blog.didispace.com/spring-boot-rabbitmq/


## Mac OS X安装

在Mac OS X中使用brew工具，可以很容易的安装RabbitMQ的服务端，只需要按如下命令操作即可：

* brew更新到最新版本，执行：`brew update
* 安装Erlang，执行：`brew install erlang`
* 安装RabbitMQ Server，执行：`brew install rabbitmq`

通过上面的命令，RabbitMQ Server的命令会被安装到`/usr/local/sbin`，并不会自动加到用户的环境变量中去，
所以我们需要在`.bash_profile`或`.profile`文件中增加下面内容：

    PATH=$PATH:/usr/local/sbin


这样，我们就可以通过rabbitmq-server命令来启动RabbitMQ的服务端了

## Docker

`docker run -d --hostname my-rabbit --name some-rabbit rabbitmq:3`

If you give that a minute, then do `docker logs` some-rabbit, you'll see in the output a block similar to:


    =INFO REPORT==== 6-Jul-2015::20:47:02 ===
    node           : rabbit@my-rabbit
    home dir       : /var/lib/rabbitmq
    config file(s) : /etc/rabbitmq/rabbitmq.config
    cookie hash    : UoNOcDhfxW9uoZ92wh6BjA==
    log            : tty
    sasl log       : tty
    database dir   : /var/lib/rabbitmq/mnesia/rabbit@my-rabbit
    
    
Note the database dir there, especially that it has my "Node Name" appended to the end for the file storage. This image makes all of `/var/lib/rabbitmq` a volume by default.


## Web管理界面

url: [http://localhost:15672/](http://localhost:15672/)

username: guest

password: guest

