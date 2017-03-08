;//AustriaCatherineFIBONACCI.asm - Fibonacci sequence
;//For Fun - Wed Mar 8, 2017

.386
.model flat, stdcall
.stack 4096
ExitProcess proto, dwExitCode:dword

;// A bunch of data needed for the lab
.data
;//indents make Steve happy
fibs DWORD 1, 1, myCount DUP(? )

.code
main proc

mov ebx, 1 ;//fib (n-1) goes here
mov edx, 1 ;//fib (n-2) goes here

mov ecx, myCount
mov esi, 8 ;//start off at the third item

LFIB:
	mov eax, ebx ;//fib(n) = fib(n-1)
	add eax, edx;//fib(n) = fib(n-1) + fib(n-2)
	mov fibs[esi], eax;// store our result

	mov edx, ebx;//fib (n-1) is now fib(n-2)
	mov ebx, eax;//fib (n) is now fib (n-1)
	add esi, TYPE fibs

loop LFIB

invoke ExitProcess, 0
main endp
end main