from typing import Self

class CaesarCipherDemo(object):

	def encode(self: Self, message: str, shift: int) -> str:
		raise NotImplementedError('Not Implemented')
	
	def decode(self: Self, cipher: str, shift: int) -> str:
		raise NotImplementedError('Not Implemented')
