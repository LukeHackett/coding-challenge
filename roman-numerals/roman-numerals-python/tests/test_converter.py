import unittest
from numerals.converter import RomanNumeralConverterDemo

class RomanNumeralConverterDemoTest(unittest.TestCase):

    @classmethod
    def setUpClass(self):
        self.numerals = RomanNumeralConverterDemo()

    def test_shouldRaiseErrorWhenValueIsLessThan0(self):
        # Given
        value: int = 0

        # When
        with self.assertRaises(Exception) as context:
            self.numerals.toNumerals(value)

        # Then
        self.assertEqual(str(context.exception), 'Input must be greater than 0')

    def test_shouldRaiseErrorWhenValueIsGreaterThan999(self):
        # Given
        value: int = 1000

        # When
        with self.assertRaises(Exception) as context:
            self.numerals.toNumerals(value)

        # Then
        self.assertEqual(str(context.exception), 'Input must be greater than 0')

if __name__ == '__main__':
    unittest.main()
