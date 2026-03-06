import {themes as prismThemes} from 'prism-react-renderer';
import type {Config} from '@docusaurus/types';
import type * as Preset from '@docusaurus/preset-classic';

const config: Config = {
  title: 'DeskUI',
  tagline: 'Professional UI components for business applications',
  favicon: 'img/favicon.ico',
  future: { v4: true },
  url: 'https://prince-devc.github.io',
  baseUrl: '/DeskUI/',
  organizationName: 'Prince-devC',
  projectName: 'DeskUI',
  onBrokenLinks: 'throw',
  i18n: { defaultLocale: 'en', locales: ['en'] },
  presets: [
    [
      'classic',
      {
        docs: {
          sidebarPath: './sidebars.ts',
          editUrl: 'https://github.com/Prince-devC/DeskUI/tree/main/docs/',
        },
        blog: false,
        theme: { customCss: './src/css/custom.css' },
      } satisfies Preset.Options,
    ],
  ],
  themeConfig: {
    colorMode: { defaultMode: 'light', respectPrefersColorScheme: true },
    navbar: {
      title: 'DeskUI',
      items: [
        { type: 'docSidebar', sidebarId: 'docsSidebar', position: 'left', label: 'Docs' },
        { to: '/docs/components/status-badge', label: 'Components', position: 'left' },
        { to: '/docs/layout/scaffold', label: 'Layout', position: 'left' },
        { href: 'https://github.com/Prince-devC/DeskUI', label: 'GitHub', position: 'right' },
      ],
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Documentation',
          items: [
            { label: 'Getting Started', to: '/docs/getting-started' },
            { label: 'Theme', to: '/docs/theme' },
            { label: 'Components', to: '/docs/components/status-badge' },
          ],
        },
        {
          title: 'Resources',
          items: [
            { label: 'GitHub', href: 'https://github.com/Prince-devC/DeskUI' },
          ],
        },
      ],
      copyright: `Copyright © ${new Date().getFullYear()} DeskUI. Built for business.`,
    },
    prism: {
      theme: prismThemes.github,
      darkTheme: prismThemes.dracula,
      additionalLanguages: ['kotlin', 'swift'],
    },
  } satisfies Preset.ThemeConfig,
};

export default config;
