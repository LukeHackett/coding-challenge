import unittest
from caesar.cipher import CaesarCipherDemo

class CaesarCipherDemoTest(unittest.TestCase):

    @classmethod
    def setUpClass(self):
        self.caesar = CaesarCipherDemo()

    def test_shouldEncodeABasicMessage(self):
        # Given
        message: str = 'AbC'
        shift: int = 3

        # When
        cipher: str = self.caesar.encode(message, shift)

        # Then
        self.assertEqual(cipher, 'DEF', 'Encoded message does not match the expected value')

    def test_shouldEncodeAnAdvancedMessage(self):
        # Given
        message: str = 'QT34C- 1W'
        shift: int = 10

        # When
        cipher: str = self.caesar.encode(message, shift)

        # Then
        self.assertEqual(cipher, '03ABMHJ_6', 'Encoded message does not match the expected value')

    def test_shouldDecodeABasicMessage(self):
        # Given
        message: str = 'DEF'
        shift: int = 3

        # When
        cipher: str = self.caesar.decode(message, shift)

        # Then
        self.assertEqual(cipher, 'ABC', 'Decoded message does not match the expected value')

    def test_shouldDecodeAnAdvancedMessage(self):
        # Given
        message: str = 'A7D 4-_GD'
        shift: int = 7

        # When
        cipher: str = self.caesar.decode(message, shift)

        # Then
        self.assertEqual(cipher, 'HBKG_EFNK', 'Decoded cipher does not match the expected value')

if __name__ == '__main__':
    unittest.main()
