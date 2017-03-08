;//AustriaCatherine_L010.asm - declarations of data types and arrays
;//L010 Wed Feb 22, 2017

.386
.model flat, stdcall
.stack 4096
ExitProcess proto, dwExitCode:dword

;// A bunch of data needed for the lab
.data
	;//indents make Steve happy
	three DWORD 12345678h								;//used for later manipulation

;// Arrays
	intarray DWORD 10000h, 20000h, 30000h, 40000h		;//array to be manipulated in later loops
	len DWORD LENGTHOF intarray							;//total array length
	lenhalf DWORD LENGTHOF intarray / 2					;//half of total array length

.code
main proc
	;// Part 0
	mov	bx, WORD PTR three				;//lower word
	xchg bx, WORD PTR[three + 2]		;//exchange with upper word
	mov WORD PTR three, bx				;//move the previous lower word to upper word spot

	;// Part 1
	mov dl, 0FFh
	inc dl

	;//Part 2a - 4.5.4 (copied exactly book implementation)
	mov edi, OFFSET intarray
	mov ecx, LENGTHOF intarray
	mov eax, 0
L1:
	add eax, [edi]
	add edi, TYPE intarray
	loop L1

	;//Part 2b
	mov edi, OFFSET intarray
	mov ebx, 0								;//think of this as the current array index
	mov ecx, LENGTHOF intarray
	mov eax, 0								;//ready eax to be added to
L2:
	add eax, [edi + (TYPE intarray*ebx)]	;//add to eax the value of the offset plus the current index
	inc ebx									;//increment the "index" of the array
	loop L2

	;//Part 3 - I think I might actually hate arrays now...
	mov eax, 0								;//reset these so that no data errors will happen
	mov ebx, 0
	mov ecx, 0
	mov edx, 0
	mov edi, OFFSET intarray
	mov ecx, lenhalf						;//set counter half array size
	mov eax, len							;//keeps track of end of array
	dec eax									;//decrement it so we don't get array out of bounds
L3:
	mov edx, [edi+(TYPE intarray * ebx)]	;//take value in current array element
	xchg edx, [edi + (TYPE intarray * eax)]	;//swap that value with the end array element
	mov[edi + (TYPE intarray * ebx)], edx	;//put the end array element in the current array element
	dec eax									;//counter for keeping track of the end of the array element
	inc ebx									;//counter for the near to beginning part of the array
	loop L3
	invoke ExitProcess,0
main endp
end main