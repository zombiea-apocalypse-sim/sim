
run:
	java zombies.ZombieSimulator

all:
	rm -f zombies/*.class
	javac zombies/*.java
	java zombies.ZombieSimulator

clean:
	rm -f zombies/*.class

jar:
	javac zombies/*.java
	jar -cvmf zombies/manifest.txt ZombieSim2015.jar zombies/*.class zombies/resources
	rm -f zombies/*.class
