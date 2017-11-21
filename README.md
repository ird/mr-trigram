Example usage:
```
~/dev/hadoop-2.9.0/bin$ gradle jar
~/dev/hadoop-2.9.0/bin$ ./hadoop jar /home/david/dev/mr-trigrams/build/libs/mr-trigrams.jar uk.org.ird.TriCount ~/test ~/out4
~/dev/hadoop-2.9.0/bin$ cat ~/out4/part-00000 | grep -i "the"
THE	240
The	100
the	1128
```
