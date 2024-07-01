import unittest
from rainwater.rainwater import RainWaterDemo

class RainWaterDemoTest(unittest.TestCase):

    @classmethod
    def setUpClass(self):
        self.rainwater = RainWaterDemo()

    def test_shouldEncodeABasicMessage(self):
        # Given
        data = []

        # When
        count: int = self.rainwater.capture(data)

        # Then
        self.assertEqual(count, 0)

    def test_shouldEncodeAnAdvancedMessage(self):
        # Given
        data = [4, 2, 1, 3, 0, 1, 2]
        
        # When
        count: int = self.rainwater.capture(data)

        # Then
        self.assertEqual(count, 6)

if __name__ == '__main__':
    unittest.main()
