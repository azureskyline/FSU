$i = 0
$k = (j+13)/27
$num = 10

while $k < $num  do
   $k += 1
   i = 3 * k - 1
end

puts("k = #$k")

case k
	when 1, 2
	  	j = 2 * k - 1
	when 3, 5
	  	j = 3 * k + 1
	when 4
		j = 4 * k - 1
	when 6, 7, 8
		j = k - 2
	else
	  ...
end

