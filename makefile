
run:
	java zombies.ZombieSimulator

all:
	rm -f zombies/*.class
	javac zombies/*.java
	java zombies.ZombieSimulator

clean:
	rm -f zombies/*.class