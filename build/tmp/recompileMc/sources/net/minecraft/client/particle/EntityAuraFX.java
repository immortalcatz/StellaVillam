package net.minecraft.client.particle;

import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityAuraFX extends EntityFX
{
    protected EntityAuraFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double speedIn)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, speedIn);
        float f = this.rand.nextFloat() * 0.1F + 0.2F;
        this.particleRed = f;
        this.particleGreen = f;
        this.particleBlue = f;
        this.setParticleTextureIndex(0);
        this.setSize(0.02F, 0.02F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.5F;
        this.xSpeed *= 0.019999999552965164D;
        this.ySpeed *= 0.019999999552965164D;
        this.zSpeed *= 0.019999999552965164D;
        this.particleMaxAge = (int)(20.0D / (Math.random() * 0.8D + 0.2D));
    }

    public void moveEntity(double x, double y, double z)
    {
        this.setEntityBoundingBox(this.getEntityBoundingBox().offset(x, y, z));
        this.resetPositionToBB();
    }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        this.moveEntity(this.xSpeed, this.ySpeed, this.zSpeed);
        this.xSpeed *= 0.99D;
        this.ySpeed *= 0.99D;
        this.zSpeed *= 0.99D;

        if (this.particleMaxAge-- <= 0)
        {
            this.setExpired();
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new EntityAuraFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
        }

    @SideOnly(Side.CLIENT)
    public static class HappyVillagerFactory implements IParticleFactory
        {
            public EntityFX getEntityFX(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                EntityFX entityfx = new EntityAuraFX(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
                entityfx.setParticleTextureIndex(82);
                entityfx.setRBGColorF(1.0F, 1.0F, 1.0F);
                return entityfx;
            }
        }
}