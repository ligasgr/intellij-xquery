FROM centos:latest
MAINTAINER Ron Hitchens
ADD MarkLogic-9.0-1.1.x86_64.rpm /tmp
EXPOSE 7000-9999
RUN mkdir -p /opt/appserver-root
VOLUME /opt/appserver-root
RUN yum install -y gdb lsb-core-amd64 libc.so.6 initscripts
RUN rpm --install /tmp/MarkLogic-9.0-1.1.x86_64.rpm
ADD run-cmd.sh /opt/MarkLogic/bin
RUN chmod a+x /opt/MarkLogic/bin/run-cmd.sh
ENTRYPOINT /opt/MarkLogic/bin/run-cmd.sh

