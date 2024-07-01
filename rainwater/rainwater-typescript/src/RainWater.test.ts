import { RainWater, RainWaterDemo } from './RainWater';

describe('RainWaterDemo', () => {
  let rainwater: RainWater;

  beforeAll(() => {
    rainwater = new RainWaterDemo();
  })

  describe('#capture', () => {
    it('should return zero when the input array is empty', () => {
      // Given
      const data: number[] = [];

      // When
      const count: number = rainwater.capture(data);

      // Then
      expect(count).toBe(0);
    });

    it('should return the correct value', () => {
      // Given
      const data: number[] = [4, 2, 1, 3, 0, 1, 2];

      // When
      const count: number = rainwater.capture(data);

      // Then
      expect(count).toBe(6);
    });
  });
});
