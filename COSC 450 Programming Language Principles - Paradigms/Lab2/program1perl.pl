#Catherine Austria 10/4/2016
# The program calls func2 mainly, if the bool is true then func1 is called and the print functions for
# var1 and var2 is called . And if bool is false then func1 is NOT called and var2 prints.
use warnings;

my $var2 = 1;
my $bool = 0;
local $var1 = 99;

sub func1 {
	my $num = $_[0];
	local $var1 = $num;
	print1();
	print2();
}

sub func2{
	my $var1 = 2;
	if ($bool){
		func1(5);
		print1();
		print2();
	} else{
		print2();
	}
	
}
sub print1{
	print "var1: ".$var1."\r\n";
}
sub print2 {
	print "var2: ".$var2."\r\n\n";
}

func2();
$bool= 1;
func2();