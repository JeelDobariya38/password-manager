import type { NextConfig } from "next";

const isProd = process.env.NODE_ENV === "production";
const nextConfig: NextConfig = {
  output: "export",
  images: {
    loader: "custom",
    loaderFile: "src/libs/custom-image-loader.ts",
  },
  basePath: isProd ? "/Passcodes" : "",
  assetPrefix: isProd ? "/Passcodes/" : "",
};

export default nextConfig;
