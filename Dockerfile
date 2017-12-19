FROM debian:jessie

RUN apt-get update && apt-get install -y \
			--no-install-recommends wget \
			#==========
			# Java JDK
			#==========
			--no-install-recommends openjdk-7-jdk \
			#======
			# XVFB
			#======
			--no-install-recommends xauth \
			--no-install-recommends xvfb \
			#=======
			# Maven
			#=======
			--no-install-recommends maven && \
		#===========================
		# Google Chrome and Firefox
		#===========================
		wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - && \
		echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list && \
		echo "deb http://packages.linuxmint.com debian import" >> /etc/apt/sources.list && \
		apt-get update && apt-get install -y --force-yes \
			--no-install-recommends google-chrome-stable \
			--no-install-recommends firefox && \
		#=========
		# Cleanup
		#=========
		apt-get clean && \
		rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/* && \
		#================
		# Code Directory
		#================
		mkdir -p /istockphoto/src/test/java

#=============================
# Directory with dependencies
#=============================
ADD lib /lib 


#================================
# Copy Maven Dependencies Folder
#================================
RUN mkdir /root/.m2 && \
		tar -zxvf /lib/repository.tar.gz -C /root/.m2

#===============
# Testing code
#===============
VOLUME /istockphoto

#=============================
# Maven repository (optional)
#=============================
VOLUME /root/.m2

#===========================================================
# Variables for parameters
# BROWSER -> The browser to be tested on (firefox, chrome)
# MAVEN_COMMAND -> Run a single method, test or suite 
#===========================================================
ENV BROWSER chrome
ENV MAVEN_COMMAND ""

ENV DISPLAY :0.0

CMD xvfb-run --auto-servernum --server-num=0 mvn ${MAVEN_COMMAND} test -f istockphoto/pom.xml -Dbrowser=${BROWSER}