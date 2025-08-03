import type { NextConfig } from "next";

const isProd = process.env.NODE_ENV === "production";
const nextConfig: NextConfig = {
  output: "export",
  images: { unoptimized: true },
  assetPrefix: isProd ? "/Passcodes/" : "",
  basePath: isProd ? "/Passcodes" : "",
};

export default nextConfig;
