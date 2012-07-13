#Export the variables fot the nameservice
IP=$(ifconfig | awk -F':' '/inet addr/&&!/127.0.0.1/{split($2,_," ");print _[1]}')
export ORBInitRef=NameService=corbaname::$IP
export ORBDefaultInitRef=corbaloc::iiop:localhost

#Launch the application
rosrun ocl deployer-corba-gnulinux -s solution.xml 

