export interface RainWater {
  capture: (data: number[]) => number;
}

export class RainWaterDemo implements RainWater {

  public capture(data: number[]): number {
    throw new Error('Not Implemented');
  };

}